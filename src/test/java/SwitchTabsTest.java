import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;

import static org.junit.Assert.assertTrue;

@Feature("Проверка переключений вкладок")
public class SwitchTabsTest {
    WebDriver driver;
    MainPage mainPage;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void setUp() {
        driver = factory.getDriver();
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Сменить вкладку на 'Соусы'")
    public void shouldSwitchOnSaucesTab() {
        mainPage.setSaucesTab();
        assertTrue(mainPage.isSaucesTabActive());

    }

    @Test
    @DisplayName("Сменить вкладку на 'Начинки'")
    public void shouldSwitchOnFillingsTab() {
        mainPage.setFillingsTab();
        mainPage.isFillingsTabActive();
    }

    @Test
    @DisplayName("Сменить вкладку на 'Булки'")
    public void shouldSwitchOnBunsTab() {
        mainPage.setFillingsTab();
        mainPage.setBunsTab();
        mainPage.isBunsTabActive();

    }
}
