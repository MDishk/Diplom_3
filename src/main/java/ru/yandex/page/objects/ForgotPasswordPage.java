package ru.yandex.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Локатор для кнопки "Войти"
    private final By enterButton = By.xpath(".//a[@class='Auth_link__1fOlj' and @href='/login']");

    @Step("Клик по кнопке 'Выйти'")
    public void clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        driver.findElement(enterButton).click();
    }
}
