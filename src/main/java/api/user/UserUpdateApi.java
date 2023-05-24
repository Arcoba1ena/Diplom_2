package api.user;

import api.RequestApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserUpdateApi extends RequestApi {
    private final static String method = "/auth/user";

    @Step("request PATCH | method '/auth/user' [with token]")
    public String requestUserUpdate(Object model, String token, Integer code) {
        Response response = given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .patch(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    @Step("request PATCH | method '/auth/user' [without token]")
    public String requestUserUpdate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .patch(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}