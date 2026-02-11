import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object.MainPage;

public class DriverFactory extends ExternalResource {
    private static WebDriver driver;
    MainPage mainPage = new MainPage(driver);


    public WebDriver getDriver() {
        return driver;
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser" ))) {
            startYandex();
        } else {
            startChrome();
        }
    }

    private void startYandex() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage" );
        System.setProperty("webdriver.chrome.driver", "C:/Users/user/Downloads/yandexdriver-25.12.0.2197-win64/yandexdriver.exe" );
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(mainPage.url);

    }

    private void startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage" );
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(mainPage.url);
    }

    @Override
    protected void before() {
        initDriver();
    }

    protected void after() {
        driver.quit();
    }
}
