package api.orders;

import api.RequestApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrdersCreateApi extends RequestApi {
    private final static String method = "/orders";

    @Step("request POST | method '/orders' [without token]")
    public String requestOrdersCreate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    @Step("request POST | method '/orders' [with token]")
    public String requestOrdersCreate(Object model, String token, Integer code) {
        Response response = given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}