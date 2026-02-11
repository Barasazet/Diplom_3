import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegistrationPage;
import ru.stellarburgers.pages.ResetPasswordPage;

import static org.junit.Assert.assertTrue;

@Feature("Тестирование алгоритмов авторизации пользователя")
public class LoginAlgorithmTest {
    String name;
    String email;
    String password;
    String token;
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registerPage;
    ResetPasswordPage resetPasswordPage;
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
        registerPage = new RegistrationPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        apiSteps = new ApiSteps();
        RestAssured.baseURI = mainPage.url;
        apiSteps.createUser(loginUserData);
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Кнопка 'Войти в аккаунт' переводит на страницу авторизации. После заполнения формы - пользователь авторизуется")

    public void shouldLoginUsingLoginButtonOnMainPage() {

        mainPage.clickOnLoginButtonMainPage();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        //Кнопка "Оформить заказ" доступна только авторизированным пользователям
        assertTrue(mainPage.isCreateOrderButtonDisplayed());

    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'")
    @Description("Кнопка 'Личный кабинет' переводит на страницу авторизации. После заполнения формы - пользователь авторизуется")
    public void shouldLoginUsingPersonalAccount() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickOnLoginButton();

        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через ссылку 'Войти' на странице регистрации")
    @Description("Ссылка 'Войти' переводит на страницу авторизации. После заполнения формы - пользователь авторизуется")

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
    @DisplayName("Авторизация через ссылку 'Войти' на странице восстановления пароля")
    @Description("Ссылка 'Войти' переводит на страницу авторизации. После заполнения формы - пользователь авторизуется")

    public void shouldLoginFromResetPasswordPage() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnBeginResetPasswordLink();
        resetPasswordPage.clickOnLoginLink();
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
