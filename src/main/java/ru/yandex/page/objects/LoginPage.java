package ru.yandex.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор для поля с Email
    private final By emailInput = By.xpath(".//input[@name='name']");
    //Локатор для поля с Паролем
    private final By passwordInput = By.xpath(".//input[@type='password']");
    //Локатор для кнопки "Войти"
    private final By enterButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0')]");
    //Локатор для кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath(".//a[@href='/register']");
    //Локатор для кнопки "Восстановить пароль"
    private final By forgotPasswordButton = By.xpath(".//a[@href='/forgot-password']");

    @Step("Открытие страницы авторизации")
    public LoginPage openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public LoginPage clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public LoginPage clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));
        driver.findElement(forgotPasswordButton).click();
        return this;
    }

    @Step("Авторизация юзера с почтой и паролем")
    public LoginPage userLogin(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        clickEnterButton();
        return this;
    }

    @Step("Отображение актуального урла")
    public LoginPage actualUrl() {
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        return this;
    }
}
