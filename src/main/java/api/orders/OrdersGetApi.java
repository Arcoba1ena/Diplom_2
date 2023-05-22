package api.orders;

import api.RequestApi;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class OrdersGetApi extends RequestApi {
    public String requestOrdersGet(Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get("/orders");
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    public String requestOrdersGet(String token, Integer code) {
        Response response = given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .when()
                .get("/orders");
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}