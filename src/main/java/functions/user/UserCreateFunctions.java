package functions.user;

import api.user.UserCreateApi;
import io.restassured.response.Response;
import models.request.user.UserCreateRequestModel;

public class UserCreateFunctions extends UserCreateApi {
    public Response getUserCreate(String name, String email, String password) {
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);
        return requestUserCreate(requestModel);
    }
}