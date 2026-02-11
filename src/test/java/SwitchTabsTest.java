import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@Feature("Проверка переключений вкладок" )
public class SwitchTabsTest {
    WebDriver driver;
    PageObject pageObject;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void setUp() {
        driver = factory.getDriver();
        pageObject = new PageObject(driver);
    }

    @Test
    @DisplayName("Сменить вкладку на 'Соусы'" )
    public void shouldSwitchOnSaucesTab() {
        pageObject.setSaucesTab();
        assertTrue(pageObject.isSaucesTabActive());

    }

    @Test
    @DisplayName("Сменить вкладку на 'Начинки'" )
    public void shouldSwitchOnFillingsTab() {
        pageObject.setFillingsTab();
        pageObject.isFillingsTabActive();
    }

    @Test
    @DisplayName("Сменить вкладку на 'Булки'" )
    public void shouldSwitchOnBunsTab() {
        pageObject.setFillingsTab();
        pageObject.setBunsTab();
        pageObject.isBunsTabActive();

    }
}
