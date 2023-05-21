package functions.user;

import api.user.UserCreateApi;
import models.request.user.UserCreateRequestModel;

public class UserCreateFunctions extends UserCreateApi {
    public String getUserCreate(String name, String email, String password, Integer code) {
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);
        return requestUserCreate(requestModel, code);
    }
}