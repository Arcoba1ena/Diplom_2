package functions.user;

import api.user.UserUpdateApi;
import models.request.user.UserUpdateRequestModel;

public class UserUpdateFunctions extends UserUpdateApi {
    public String getUserUpdate(String name, String email, String token, Integer code){
        UserUpdateRequestModel requestModel = new UserUpdateRequestModel(name,email);
        return token == null ? requestUserUpdate(requestModel, code) : requestUserUpdate(requestModel, token, code);
    }
}