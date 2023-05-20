package functions;

import api.UserLoginApi;
import models.request.UserLoginRequestModel;

public class UserLoginFunctions extends UserLoginApi {
    public String getUserLogin(String email, String password, Integer code){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(email,password);
        return requestUserLogin(requestModel,code);
    }
}