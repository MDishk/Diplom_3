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
import ru.yandex.page.objects.LoginPage;
import ru.yandex.page.objects.MainPage;

public class TransitionsTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData newUser;
    String accessToken;

    @Before
    public void setUp() {
        newUser = CreateUser.createRandomUser();

        ValidatableResponse response = userApi.createUser(newUser);;
        accessToken = response.extract().body().path("accessToken");

        loginPage
                .openLoginPage()
                .userLogin(newUser.getEmail(), newUser.getPassword());
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет по одноименной кнопке на главной странице")
    public void goToAccountTest() {
        mainPage.clickAccountButton();

        Assert.assertTrue("Не удалось перейти в аккаунт", accountPage.checkProfileButton());
    }

    @Test
    @DisplayName("Переход в конструктор через кнопку")
    @Description("Проверка перехода в конструктор по одноименной кнопке")
    public void goToConstructorFromButtonTest() {
        mainPage.clickAccountButton();
        accountPage.clickConstructorButton();

        Assert.assertEquals("Конструктор не открылся", MainPage.MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор через логотип")
    @Description("Проверка перехода в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromLogoTest() {
        mainPage.clickAccountButton();
        accountPage.clickLogoStellarBurgers();

        Assert.assertEquals("Конструктор не открылся", MainPage.MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Разлогин через кнопку выхода")
    @Description("Проверка выхода из аккаунта через кнопку 'Выход'")
    public void logoutByExitButtonTest() {
        mainPage.clickAccountButton();
        accountPage.clickExitButton();
        loginPage.actualUrl();

        Assert.assertEquals("Не получилось выйти из аккаунта", LoginPage.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
