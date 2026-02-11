import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@Feature("Тестирование алгоритмов авторизации пользователя" )
public class LoginAlgorithmTest {
    String name;
    String email;
    String password;
    String token;
    WebDriver driver;
    PageObject pageObject;
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
        pageObject = new PageObject(driver);
        apiSteps = new ApiSteps();
        RestAssured.baseURI = pageObject.url;
        apiSteps.createUser(loginUserData);
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главной странице" )
    public void shouldLoginUsingLoginButtonOnMainPage() {

        pageObject.clickOnLoginButtonMainPage();
        pageObject.setEmail(email);
        pageObject.setPassword(password);
        pageObject.clickOnLoginButton();

        //Кнопка "Оформить заказ" доступна только авторизированным пользователям
        assertTrue(pageObject.isCreateOrderButtonDisplayed());

    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'" )
    public void shouldLoginUsingPersonalAccount() {
        pageObject.clickOnPersonalAccountButton();
        pageObject.setEmail(email);
        pageObject.setPassword(password);
        pageObject.clickOnLoginButton();

        assertTrue(pageObject.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через ссылку 'Войти' на странице регистрации" )
    public void shouldLoginFromRegisterPage() {
        pageObject.clickOnPersonalAccountButton();
        pageObject.clickOnRegistrationLink();
        pageObject.clickOnLoginLinkRegistrationPage();
        pageObject.setEmail(email);
        pageObject.setPassword(password);
        pageObject.clickOnLoginButton();

        assertTrue(pageObject.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через ссылку 'Войти' на странице восстановления пароля" )
    public void shouldLoginFromResetPasswordPage() {
        pageObject.clickOnPersonalAccountButton();
        pageObject.clickOnBeginResetPasswordButton();
        pageObject.clickOnLoginLinkRegistrationPage();
        pageObject.setEmail(email);
        pageObject.setPassword(password);
        pageObject.clickOnLoginButton();

        assertTrue(pageObject.isCreateOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        token = apiSteps.getAccessToken(loginUserData);
        apiSteps.deleteUser(loginUserData, token);
    }
}
