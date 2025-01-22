package ru.yandex.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Локатор для кнопки "Выход"
    private final By exitButton = By.xpath(".//button[contains(@class, 'Account_button__14Yp3')]");
    //Локатор для кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//a[@href='/']/p");
    //Локатор для кнопки "Профиль"
    private final By profileButton = By.xpath(".//a[@aria-current='page' and contains(text(), 'Профиль')]");
    //Локатор для логотипа Stellar Burgers
    private final By logoStellarBurgers = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    @Step("Клик по кнопке 'Выход'")
    public AccountPage clickExitButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public AccountPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    @Step("Клик по логотипу Stellar Burgers")
    public AccountPage clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
        return this;
    }

    @Step("Проверка на отображение кнопки 'Профиль'")
    public boolean checkProfileButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        return driver.findElement(profileButton).isDisplayed();
    }
}
