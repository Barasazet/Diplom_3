package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public final String url = "https://stellarburgers.education-services.ru/";

    private final By personalAccountButton = By.xpath((".//p[contains(text(), 'Личный Кабинет')]" ));
   private final By pageSpace = By.xpath(".//div[@class='App_App__aOmNj']" );
    private final By loginButtonMainPage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']" );
    private final By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']" );

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







    @Step("Кликнуть на кнопку 'Войти в аккаунт' на главной странице" )
    public void clickOnLoginButtonMainPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonMainPage));
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMainPage));
        driver.findElement(loginButtonMainPage).click();
    }



    @Step("Появилась ли кнопка 'Оформить заказ'" )
    public boolean isCreateOrderButtonDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        wait.until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
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
