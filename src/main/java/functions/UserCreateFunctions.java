package functions;

import api.UserCreateApi;
import models.request.UserCreateRequestModel;

public class UserCreateFunctions extends UserCreateApi {
    public String getUserCreate(String name, String email, String password, Integer code) {
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);
        return requestUserCreate(requestModel, code);
    }
}