import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.page.api.UserApi;
import ru.yandex.page.data.CreateUser;
import ru.yandex.page.data.UserData;

public class EnterTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData newUser;
    String accessToken;

    @Before
    public void setUp() {
        newUser = CreateUser.createRandomUser();
        ValidatableResponse response = userApi.createUser(newUser);
        accessToken = response.extract().body().path("accessToken");
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход с главной страницы")
    @Description("Проверка входа через кнопку 'Войти в аккаунт' на главной странице")
    public void enterFromMainPageTest() {
        mainPage
                .openMainPage()
                .clickEnterButton();
        loginPage
                .userLogin(newUser.getEmail(), newUser.getPassword());

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку 'Личный Кабинет' на главной странице")
    public void enterByAccountButtonTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .userLogin(newUser.getEmail(), newUser.getPassword());

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Вход со страницы регистрации")
    @Description("Проверка входа через кнопку 'Войти' на странице регистрации")
    public void enterFromRegisterPageTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .clickRegisterButton();
        registerPage
                .clickEnterButton();
        loginPage
                .userLogin(newUser.getEmail(), newUser.getPassword());

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }

    @Test
    @DisplayName("Вход со страницы восстановления пароля")
    @Description("Проверка входа через кнопку 'Войти' на странице восстановления пароля")
    public void enterFromForgotPasswordPageTest(){
        mainPage
                .openMainPage()
                .clickAccountButton();
        loginPage
                .clickForgotPasswordButton();
        forgotPasswordPage
                .clickEnterButton();
        loginPage
                .userLogin(newUser.getEmail(), newUser.getPassword());

        Assert.assertTrue("Не удалось войти в аккаунт", mainPage.checkCreateOrderButton());
    }
}