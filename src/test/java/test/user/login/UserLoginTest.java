package test.user.login;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import functions.UserLoginFunctions;
import functions.UserCreateFunctions;
import models.response.UserResponseModel;
import io.qameta.allure.junit4.DisplayName;

import static api.UserDeleteApi.requestDelete;
import static functions.base.Util.deserialize;

@RunWith(Parameterized.class)
public class UserLoginTest extends UserLoginFunctions {

    @Before
    public void domain() {
        setUp();
    }

    private final String name;
    private final String email;
    private final String password;

    public UserLoginTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUsers", "testUsers@yandex.ru", "uniqueP@sw0rds"}
        };
    }

    UserResponseModel loginResponse;
    UserCreateFunctions userCreate = new UserCreateFunctions();

    @Test
    @DisplayName("Авторизация пользователя - проверка под существующим пользователем")
    public void userCreateCheckAuth() {
        userCreate.getUserCreate(name, email, password, 200);
        loginResponse = deserialize(getUserLogin(email, password, 200),
                UserResponseModel.class);

        Assert.assertTrue(loginResponse.success);
        requestDelete(loginResponse.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным email")
    public void userCreateCheckUnValidEmail() {
        UserResponseModel createResponse = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        loginResponse = deserialize(getUserLogin(email + "/", password, 401),
                UserResponseModel.class);

        Assert.assertFalse(loginResponse.success);
        requestDelete(createResponse.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным password")
    public void userCreateCheckUnValidPassword() {
        UserResponseModel createResponse = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        loginResponse = deserialize(getUserLogin(email, password + "/", 401),
                UserResponseModel.class);

        Assert.assertFalse(loginResponse.success);
        requestDelete(createResponse.getAccessToken());
    }
}