package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserLoginApi extends RequestApi{
    public String requestUserLogin(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/login");
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}