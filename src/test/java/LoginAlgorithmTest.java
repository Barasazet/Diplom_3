import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegisterPage;

import static org.junit.Assert.assertTrue;

@Feature("Тестирование алгоритмов авторизации пользователя" )
public class LoginAlgorithmTest {
    String name;
    String email;
    String password;
    String token;
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ApiSteps apiSteps;
    LoginUserData loginUserData;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void setUp() {
        name = RandomStringUtils.randomAlphabetic(13);
        email = RandomStringUtils.randomAlphabetic(13) + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(13);
        loginUserData = new LoginUserData(name, email, password);
        driver = factory.getDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        apiSteps = new ApiSteps();
        RestAssured.baseURI = mainPage.url;
        apiSteps.createUser(loginUserData);
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главной странице" )
    public void shouldLoginUsingLoginButtonOnMainPage() {

        mainPage.clickOnLoginButtonMainPage();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        //Кнопка "Оформить заказ" доступна только авторизированным пользователям
        assertTrue(mainPage.isCreateOrderButtonDisplayed());

    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'" )
    public void shouldLoginUsingPersonalAccount() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через ссылку 'Войти' на странице регистрации" )
    public void shouldLoginFromRegisterPage() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        registerPage.clickOnLoginLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через ссылку 'Войти' на странице восстановления пароля" )
    public void shouldLoginFromResetPasswordPage() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnBeginResetPasswordLink();
        registerPage.clickOnLoginLink();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        token = apiSteps.getAccessToken(loginUserData);
        apiSteps.deleteUser(loginUserData, token);
    }
}
