package test.user.update;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import functions.user.UserUpdateFunctions;
import functions.user.UserCreateFunctions;
import models.response.user.UserResponseModel;
import io.qameta.allure.junit4.DisplayName;
import models.response.user.UserErrorResponseModel;
import models.response.user.UserUpdateResponseModel;

import static functions.Util.*;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class UserUpdateTest extends UserUpdateFunctions {

    @Before
    public void domain() {
        apiEndPoint();
    }

    private final String name;
    private final String email;
    private final String password;

    private final String updName;
    private final String updEmail;

    public UserUpdateTest(String name, String email, String password, String updName, String updEmail) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.updName = updName;
        this.updEmail = updEmail;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2},{3},{4}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUsers", "testUsers@yandex.ru", "uniqueP@sw0rds", "updateUsers", "updateUsers@yandex.ru"}
        };
    }

    UserCreateFunctions userCreate = new UserCreateFunctions();

    UserResponseModel responseCreate;
    UserErrorResponseModel responseError;
    UserUpdateResponseModel responseUpdate;

    @Test
    @DisplayName("Обновление пользователя - проверка обновления поля [name]")
    public void userUpdateCheckName() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseUpdate = deserialize(getUserUpdate(updName, email, responseCreate.getAccessToken(), 200),
                UserUpdateResponseModel.class);

        Assert.assertEquals(updName, responseUpdate.user.name);
    }

    @Test
    @DisplayName("Обновление пользователя - проверка обновления поля [email]")
    public void userUpdateCheckEmail() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseUpdate = deserialize(getUserUpdate(name, updEmail, responseCreate.getAccessToken(), 200),
                UserUpdateResponseModel.class);

        Assert.assertEquals(updEmail.toLowerCase(), responseUpdate.user.email.toLowerCase());
    }

    @Test
    @DisplayName("Обновление пользователя - проверка обновления поля [email]")
    public void userUpdateCheckWithOutAuth() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseError = deserialize(getUserUpdate(updName, updEmail, null, 401),
                UserErrorResponseModel.class);

        Assert.assertEquals("You should be authorised", responseError.message);
    }

    @After
    public void userDelete() {
        getUserDelete(responseCreate.getAccessToken());
    }
}