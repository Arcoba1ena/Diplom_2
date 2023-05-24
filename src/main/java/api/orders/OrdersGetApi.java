package api.orders;

import api.RequestApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class OrdersGetApi extends RequestApi {

    private final static String method = "/orders";

    @Step("request GET | method '/orders' [without token]")
    public String requestOrdersGet(Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    @Step("request GET | method '/orders' [with token]")
    public String requestOrdersGet(String token, Integer code) {
        Response response = given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .when()
                .get(method);
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}