package functions;

import models.request.UserCreateRequestModel;

public class UserCreate extends RequestApi {

    public void getUserCreate(String name, String email, String password, Integer code){
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);

        requestPost(requestModel, code).getBody();
    }
}