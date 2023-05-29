package functions.user;

import api.user.UserUpdateApi;
import io.restassured.response.Response;
import models.request.user.UserUpdateRequestModel;

public class UserUpdateFunctions extends UserUpdateApi {
    public Response getUserUpdate(String name, String email, String token){
        UserUpdateRequestModel requestModel = new UserUpdateRequestModel(name,email);
        return token == null ? requestUserUpdate(requestModel) : requestUserUpdate(requestModel, token);
    }
}