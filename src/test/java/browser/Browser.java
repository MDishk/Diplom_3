package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser {

    protected static WebDriver driver;

    public static WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/java/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");

        BrowserType browserType = BrowserType.valueOf(browserProperty);
        switch (browserType) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "");
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser undefined");
        }
        return driver;
    }
}