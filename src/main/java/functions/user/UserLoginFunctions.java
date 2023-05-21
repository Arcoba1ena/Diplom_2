package functions.user;

import api.user.UserLoginApi;
import models.request.user.UserLoginRequestModel;

public class UserLoginFunctions extends UserLoginApi {
    public String getUserLogin(String email, String password, Integer code){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(email,password);
        return requestUserLogin(requestModel,code);
    }
}