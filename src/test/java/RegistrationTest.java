import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.page.api.UserApi;
import ru.yandex.page.data.UserData;
import static org.hamcrest.Matchers.is;

public class RegistrationTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData user = new UserData();
    String accessToken;

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    public void successUserRegisterTest() {
        registerPage
                .openRegisterPage()
                .registerNewUser("Satoru", "pupipu@mail.ru", "12pup56");

        ValidatableResponse response = userApi.loginUser(user.getEmail(), user.getPassword());
        accessToken = response.extract().body().path("accessToken");
        ValidatableResponse checkUserResponse = userApi.userInfo(accessToken);
        accessToken = response.extract().body().path("accessToken");
        checkUserResponse
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("success", is(true));
    }

    @Test
    public void checkErrorWrongPasswordTest() {
        registerPage
                .openRegisterPage()
                .registerNewUser("Satoru", "pupipu@mail.ru", "12p");

        Assert.assertTrue("Можно ввести пароль менее 6 символов", registerPage.checkWrongPassword());
    }
}
