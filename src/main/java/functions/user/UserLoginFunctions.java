package functions.user;

import api.user.UserLoginApi;
import io.restassured.response.Response;
import models.request.user.UserLoginRequestModel;

public class UserLoginFunctions extends UserLoginApi {
    public Response getUserLogin(String email, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(email,password);
        return requestUserLogin(requestModel);
    }
}