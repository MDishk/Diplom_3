import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.page.api.UserApi;
import ru.yandex.page.data.UserData;

public class EnterTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData user = new UserData();
    String accessToken;

    @Before
    public void setUp() {
        ValidatableResponse response = userApi.createUser(user);
        accessToken = response.extract().body().path("accessToken");
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    public void enterFromMainPageTest() {
        mainPage
                .openMainPage()
                .clickEnterButton();
        loginPage
                .userLogin("pupipu@mail.ru", "12pup56");

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    public void enterByAccountButtonTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .userLogin("pupipu@mail.ru", "12pup56");

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    public void enterFromRegisterPageTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .clickRegisterButton();
        registerPage
                .clickEnterButton();
        loginPage
                .userLogin("pupipu@mail.ru", "12pup56");

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    public void enterFromForgotPasswordPageTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .clickForgotPasswordButton();
        forgotPasswordPage
                .clickEnterButton();
        loginPage
                .userLogin("pupipu@mail.ru", "12pup56");

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }
}