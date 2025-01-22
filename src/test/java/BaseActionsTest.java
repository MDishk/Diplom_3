import browser.Browser;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.page.objects.*;
import java.io.IOException;

public class BaseActionsTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registerPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected AccountPage accountPage;

    @Before
    public void startUp() throws IOException {
        driver = Browser.initDriver();
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        accountPage = new AccountPage(driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
