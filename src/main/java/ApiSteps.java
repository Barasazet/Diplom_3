import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiSteps {
    @Step("Получение access-токена")
    public String getAccessToken(LoginUserData loginUserData) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(loginUserData)
                        .when()
                        .post("/api/auth/login");

        return response.then().extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(LoginUserData loginUserData, String token) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .and()
                .body(loginUserData)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Создание пользователя")
    public Response createUser(LoginUserData loginUserData) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginUserData)
                .when()
                .post("/api/auth/register");
    }

}
