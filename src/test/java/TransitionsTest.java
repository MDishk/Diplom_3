import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.page.api.UserApi;
import ru.yandex.page.data.UserData;
import ru.yandex.page.objects.LoginPage;
import ru.yandex.page.objects.MainPage;

public class TransitionsTest extends BaseActionsTest {

    UserApi userApi = new UserApi();
    UserData user = new UserData();
    String accessToken;

    @Before
    public void setUp() {
        ValidatableResponse response = userApi.createUser(user);;
        accessToken = response.extract().body().path("accessToken");

        loginPage
                .openLoginPage()
                .userLogin("pupipu@mail.ru", "12pup56");
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    public void goToAccountTest() {
        mainPage.clickAccountButton();

        Assert.assertTrue("Не удалось перейти в аккаунт", accountPage.checkProfileButton());
    }

    @Test
    public void goToConstructorFromButtonTest() {
        mainPage.clickAccountButton();
        accountPage.clickConstructorButton();

        Assert.assertEquals("Конструктор не открылся", MainPage.MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void goToConstructorFromLogoTest() {
        mainPage.clickAccountButton();
        accountPage.clickLogoStellarBurgers();

        Assert.assertEquals("Конструктор не открылся", MainPage.MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void logoutByExitButtonTest() {
        mainPage.clickAccountButton();
        accountPage.clickExitButton();
        loginPage.actualUrl();

        Assert.assertEquals("Не получилось выйти из аккаунта", LoginPage.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
