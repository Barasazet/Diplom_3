import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Feature("Тестирование регистрации пользователя" )
public class RegistrationTest {
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
        driver = factory.getDriver();
        pageObject = new PageObject(driver);
        loginUserData = new LoginUserData(name, email, password);

    }

    @Test
    @DisplayName("Регистрация пользователя" )
    public void shouldCreateUser() {
        apiSteps = new ApiSteps();
        RestAssured.baseURI = pageObject.url;

        pageObject.clickOnPersonalAccountButton();
        pageObject.clickOnRegistrationLink();
        pageObject.setName(name);
        pageObject.setEmail(email);
        pageObject.setPassword(password);
        pageObject.clickOnFinishRegisterButton();
        token = apiSteps.getAccessToken(loginUserData);

        assertNotNull(token);
    }

    @Test
    @DisplayName("Появление сообщения при некорректном пароле" )
    public void invalidPasswordCauseErrorMessage() {
        pageObject.clickOnPersonalAccountButton();
        pageObject.clickOnRegistrationLink();
        pageObject.setName(name);
        pageObject.setEmail(email);
        password = RandomStringUtils.randomAlphabetic(5);
        pageObject.setPassword(password);
        pageObject.clickOnEmptySpace();

        assertTrue(pageObject.isInvalidPasswordMessageDisplayed());
    }

    @After
    public void tearDown() {
        if (token != null) {
            apiSteps.deleteUser(loginUserData, token);
        }
    }
}


