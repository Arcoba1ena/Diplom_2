package api.user;

import api.RequestApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCreateApi extends RequestApi {
    private final static String method = "/auth/register";

    @Step("request POST | method '/auth/register'")
    public String requestUserCreate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}