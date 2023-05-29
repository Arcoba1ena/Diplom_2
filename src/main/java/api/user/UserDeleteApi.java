package api.user;

import api.RequestApi;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class UserDeleteApi extends RequestApi {
    private final static String method = "/auth/user";

    @Step("request DELETE | method '/auth/user' [with token]")
    public static void requestDelete(String token) {
        given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .when()
                .delete(method);
    }

    @Step("request DELETE | method '/auth/user' [without token]")
    public static void requestDelete() {
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(method);
    }
}