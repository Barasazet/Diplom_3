package ru.stellarburgers.pages;

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

    private final By startRegistrationLink = By.xpath(".//a[contains(text(), 'Зарегистрироваться')]");
    private final By beginResetPasswordLink = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    private final By emailField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");

    @Step("Ввести email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Кликнуть на кнопку 'Войти'")
    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Кликнуть на ссылку 'Восстановить пароль'")
    public void clickOnBeginResetPasswordLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(beginResetPasswordLink));
        wait.until(ExpectedConditions.elementToBeClickable(beginResetPasswordLink));
        driver.findElement(beginResetPasswordLink).click();
    }

    @Step("Кликнуть на ссылку 'Зарегистрироваться'")
    public void clickOnRegistrationLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(startRegistrationLink));
        wait.until(ExpectedConditions.elementToBeClickable(startRegistrationLink));
        driver.findElement(startRegistrationLink).click();
    }
}
