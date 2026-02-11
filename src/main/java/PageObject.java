import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObject {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public final String url = "https://stellarburgers.education-services.ru/";

    private final By personalAccountButton = By.xpath((".//p[contains(text(), 'Личный Кабинет')]" ));
    private final By startRegistrationLink = By.xpath(".//a[contains(text(), 'Зарегистрироваться')]" );
    private final By nameField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Имя']/following-sibling::input" );
    private final By emailField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Email']/following-sibling::input" );
    private final By passwordField = By.xpath(".//label[@class='input__placeholder text noselect text_type_main-default' and text()='Пароль']/following-sibling::input" );
    private final By registerFinishButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']" );
    private final By pageSpace = By.xpath(".//div[@class='App_App__aOmNj']" );
    private final By invalidPasswordMessage = By.xpath(".//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']" );
    private final By loginButtonMainPage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']" );
    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']" );
    private final By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']" );
    private final By loginLinkRegisterPage = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']" );
    private final By beginResetPasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']" );

    private final By bunsTabUnactive = By.xpath(".//span[text()='Булки']" );
    private final By saucesTabUnactive = By.xpath(".//span[text()='Соусы']" );
    private final By fillingsTabUnactive = By.xpath(".//span[text()='Начинки']" );
    private final By bunsTabActive = By.xpath(".//span[text()='Булки']//ancestor::div[1]" );
    private final By saucesTabActive = By.xpath(".//span[text()='Соусы']//ancestor::div[1]" );
    private final By fillingsTabActive = By.xpath(".//span[text()='Начинки']//ancestor::div[1]" );

    @Step("кликнуть на 'Личный кабинет'" )
    public void clickOnPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Кликнуть на ссылку 'Зарегистрироваться'" )
    public void clickOnRegistrationLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(startRegistrationLink));
        wait.until(ExpectedConditions.elementToBeClickable(startRegistrationLink));
        driver.findElement(startRegistrationLink).click();
    }

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

    @Step("Кликнуть на кнопку 'Войти в аккаунт' на главной странице" )
    public void clickOnLoginButtonMainPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonMainPage));
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMainPage));
        driver.findElement(loginButtonMainPage).click();
    }

    @Step("Кликнуть на кнопку 'Войти'" )
    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Появилась ли кнопка 'Оформить заказ'" )
    public boolean isCreateOrderButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Кликнуть на ссылку 'Войти'" )
    public void clickOnLoginLinkRegistrationPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLinkRegisterPage));
        wait.until(ExpectedConditions.elementToBeClickable(loginLinkRegisterPage));
        driver.findElement(loginLinkRegisterPage).click();
    }

    @Step("Кликнуть на ссылку 'Восстановить пароль'" )
    public void clickOnBeginResetPasswordButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(beginResetPasswordButton));
        wait.until(ExpectedConditions.elementToBeClickable(beginResetPasswordButton));
        driver.findElement(beginResetPasswordButton).click();
    }

    @Step("Выбрать вкладку 'Булки'" )
    public void setBunsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTabUnactive));
        wait.until(ExpectedConditions.elementToBeClickable(bunsTabUnactive));
        driver.findElement(bunsTabUnactive).click();

    }

    @Step("Активна ли вкладка 'Булки'" )
    public boolean isBunsTabActive() {
        WebElement activeTab = driver.findElement(bunsTabActive);
        String tabClasses = activeTab.getAttribute("class" );
        String activeTabClass = "tab_tab_type_current__2BEPc";
        return tabClasses.contains(activeTabClass);
    }


    @Step("Выбрать вкладку 'Соусы'" )
    public void setSaucesTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTabUnactive));
        wait.until(ExpectedConditions.elementToBeClickable(saucesTabUnactive));
        driver.findElement(saucesTabUnactive).click();
    }

    @Step("Активна ли вкладка 'Соусы'" )
    public boolean isSaucesTabActive() {
        WebElement activeTab = driver.findElement(saucesTabActive);
        String tabClasses = activeTab.getAttribute("class" );
        String activeTabClass = "tab_tab_type_current__2BEPc";
        return tabClasses.contains(activeTabClass);
    }


    @Step("Выбрать вкладку 'Начинки'" )
    public void setFillingsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTabUnactive));
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTabUnactive));
        driver.findElement(fillingsTabUnactive).click();
    }

    @Step("Активна ли вкладка 'Начинки'" )
    public boolean isFillingsTabActive() {
        WebElement activeTab = driver.findElement(fillingsTabActive);
        String tabClasses = activeTab.getAttribute("class" );
        String activeTabClass = "tab_tab_type_current__2BEPc";
        return tabClasses.contains(activeTabClass);
    }


    @Step("Кликнуть на пустое место" )
    public void clickOnEmptySpace() {
        driver.findElement(pageSpace).click();
    }
}
