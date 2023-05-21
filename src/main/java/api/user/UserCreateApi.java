package api.user;

import api.RequestApi;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCreateApi extends RequestApi {
    public String requestUserCreate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/auth/register");
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}