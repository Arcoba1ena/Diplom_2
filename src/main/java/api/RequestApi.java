package api;

import io.restassured.RestAssured;

public class RequestApi {
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/auth";
    }
}