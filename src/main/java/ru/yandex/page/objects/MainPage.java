package ru.yandex.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор для кнопки "Личный Кабинет"
    private final By accountButton = By.xpath(".//a[@href='/account']");
    //Локатор для кнопки "Войти в аккаунт"
    private final By enterButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0')]");
    //Локатор для раздела "Булки"
    private final By bunsSection = By.xpath(".//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    //Локатор для раздела "Соусы"
    private final By souseSection = By.xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    //Локатор для раздела "Начинки"
    private final By toppingSection = By.xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab__1SPyG')]");
    //Локатор для кнопки "Оформить заказ"
    private final By createOrderButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Оформить заказ')]");
    //Локатор для активного раздела "Булки"
    private final By activeBunsSection = By.xpath(".//h2[contains(@class, 'text text_type_main-medium mb-6 mt-10') and contains(text(), 'Булки')]");
    //Локатор для активного раздела "Соусы"
    private final By activeSouseSection = By.xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab_type_current__2BEPc')]");
    //Локатор для активного раздела "Начинки"
    private final By activeToppingSection = By.xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    @Step("Открытие главной страницы")
    public MainPage openMainPage() {
        driver.get(MAIN_PAGE_URL);
        return this;
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public MainPage clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton));
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public MainPage clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton));
        driver.findElement(accountButton).click();
        return this;
    }

    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public boolean checkCreateOrderButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Клик по разделу 'Булки'")
    public MainPage openBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection));
        driver.findElement(bunsSection).click();
        return this;
    }

    @Step("Клик по разделу 'Соусы'")
    public MainPage openSouseSection() {
        wait.until(ExpectedConditions.elementToBeClickable(souseSection));
        driver.findElement(souseSection).click();
        return this;
    }

    @Step("Клик по разделу 'Начинки'")
    public MainPage openToppingSection() {
        wait.until(ExpectedConditions.elementToBeClickable(toppingSection));
        driver.findElement(toppingSection).click();
        return this;
    }

    @Step("Проверка активности раздела 'Булки'")
    public boolean isBunsSectionActive() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsSection));
        return driver.findElement(activeBunsSection).isDisplayed();
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSouseSectionActive() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeSouseSection));
        return driver.findElement(activeSouseSection).isDisplayed();
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isToppingSectionActive() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeToppingSection));
        return driver.findElement(activeToppingSection).isDisplayed();
    }
}
