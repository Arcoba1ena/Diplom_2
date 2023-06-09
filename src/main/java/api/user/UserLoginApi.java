package api.user;

import api.RequestApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserLoginApi extends RequestApi {
    private final static String method = "/auth/login";

    @Step("request POST | method '/auth/login'")
    public Response requestUserLogin(Object model) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post(method);
    }
}