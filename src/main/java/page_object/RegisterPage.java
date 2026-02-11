package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By nameField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Имя']/following-sibling::input" );
    private final By emailField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Email']/following-sibling::input" );
    private final By passwordField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Пароль']/following-sibling::input" );
    private final By registerFinishButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']" );
    private final By loginLink = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']" );
    private final By invalidPasswordMessage = By.xpath(".//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']" );
    private final By pageSpace = By.xpath(".//div[@class='App_App__aOmNj']" );

    @Step("Ввести имя" )
    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввести email" )
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль" )
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Кликнуть на кнопку 'Зарегистрироваться'" )
    public void clickOnFinishRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerFinishButton));
        wait.until(ExpectedConditions.elementToBeClickable(registerFinishButton));
        driver.findElement(registerFinishButton).click();
    }

    @Step("Появилось ли сообшение о некорректном пароле" )
    public boolean isInvalidPasswordMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidPasswordMessage));
        return driver.findElement(invalidPasswordMessage).isDisplayed();
    }

    @Step("Кликнуть на ссылку 'Войти'" )
    public void clickOnLoginLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
    }
    @Step("Кликнуть на пустое место" )
    public void clickOnEmptySpace() {
        driver.findElement(pageSpace).click();
    }
}

