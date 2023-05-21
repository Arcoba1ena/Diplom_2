package api;

import io.restassured.RestAssured;

public class RequestApi {
    public void apiEndPoint() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }
}