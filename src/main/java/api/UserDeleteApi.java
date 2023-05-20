package api;

import static io.restassured.RestAssured.given;

public class UserDeleteApi extends RequestApi{
    public static void requestDelete(String token) {
        given().auth()
                .oauth2(token.replace("Bearer ",""))
                .header("Content-type", "application/json")
                .when()
                .delete("/user")
                .then().statusCode(202);
    }
}