import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.page.api.UserApi;
import ru.yandex.page.data.CreateUser;
import ru.yandex.page.data.LoginUser;
import ru.yandex.page.data.UserData;
import static org.hamcrest.Matchers.is;

public class RegistrationTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData newUser;
    LoginUser login;
    String accessToken;

    @Before
    public void setUp() {
        newUser = CreateUser.createRandomUser();
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации пользователя")
    public void successUserRegisterTest() {
        registerPage
                .openRegisterPage()
                .registerNewUser(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        login = new LoginUser(newUser.getEmail(), newUser.getPassword());

        ValidatableResponse response = userApi.loginUser(login);
        accessToken = response.extract().body().path("accessToken");

        ValidatableResponse checkUserResponse = userApi.userInfo(accessToken);
        checkUserResponse
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Проверить ошибку из-за некорректного пароля")
    @Description("Проверка отображения ошибки при вводе пароля менее 6 символов")
    public void checkErrorWrongPasswordTest() {
        registerPage
                .openRegisterPage()
                .registerNewUser(newUser.getName(), newUser.getEmail(), "12p");

        Assert.assertTrue("Можно ввести пароль менее 6 символов", registerPage.checkWrongPassword());
    }
}
