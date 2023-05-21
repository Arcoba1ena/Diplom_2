package api.orders;

import api.RequestApi;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrdersCreateApi extends RequestApi {
    public String requestOrdersCreate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/orders");
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    public String requestOrdersCreate(Object model, String token, Integer code) {
        Response response = given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/orders");
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}
