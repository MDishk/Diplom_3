package ru.yandex.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор для кнопки "Войти"
    private final By enterButton = By.xpath(".//a[@href='/login']");
    //Локатор для поля "Имя"
    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    //Локатор для поля "Email"
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Локатор для поля "Пароль"
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Локатор для кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[contains(@class, 'button_button_type_primary__1O7Bx')]");
    //Локатор для ошибки "Некорректный пароль"
    private final By errorWrongPassword = By.xpath(".//p[contains(@class, 'input__error') and contains(text(), 'Некорректный пароль')]");

    @Step("Открытие страницы регистрации")
    public RegistrationPage openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
        return this;
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввод почты")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegistrationPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Регистрация нового пользователя")
    public RegistrationPage registerNewUser(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public RegistrationPage clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Проверка ошибки 'Некорректный пароль'")
    public boolean checkWrongPassword() {
        return driver.findElement(errorWrongPassword).isDisplayed();
    }
}
