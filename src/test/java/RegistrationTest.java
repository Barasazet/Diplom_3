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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Feature("Тестирование регистрации пользователя" )
public class RegistrationTest {
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
        driver = factory.getDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        loginUserData = new LoginUserData(name, email, password);

    }

    @Test
    @DisplayName("Регистрация пользователя" )
    public void shouldCreateUser() {
        apiSteps = new ApiSteps();
        RestAssured.baseURI = mainPage.url;

        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickOnFinishRegisterButton();
        token = apiSteps.getAccessToken(loginUserData);

        assertNotNull(token);
    }

    @Test
    @DisplayName("Появление сообщения при некорректном пароле" )
    public void invalidPasswordCauseErrorMessage() {
        mainPage.clickOnPersonalAccountButton();
        loginPage.clickOnRegistrationLink();
        registerPage.setName(name);
        registerPage.setEmail(email);
        password = RandomStringUtils.randomAlphabetic(5);
        registerPage.setPassword(password);
        registerPage.clickOnEmptySpace();

        assertTrue(registerPage.isInvalidPasswordMessageDisplayed());
    }

    @After
    public void tearDown() {
        if (token != null) {
            apiSteps.deleteUser(loginUserData, token);
        }
    }
}


