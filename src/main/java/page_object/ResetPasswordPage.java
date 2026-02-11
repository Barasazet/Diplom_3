package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResetPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By loginLink = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    @Step("Кликнуть на ссылку 'Войти'")
    public void clickOnLoginLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
    }


}
