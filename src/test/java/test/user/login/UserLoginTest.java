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

    UserResponseModel responseLogin;
    UserResponseModel responseCreate;
    UserCreateFunctions userCreate = new UserCreateFunctions();

    @Test
    @DisplayName("Авторизация пользователя - проверка под существующим пользователем")
    public void userLoginCheckAuth() {
        userCreate.getUserCreate(name, email, password, 200);
        responseLogin = deserialize(getUserLogin(email, password, 200),
                UserResponseModel.class);

        Assert.assertTrue(responseLogin.success);
        requestDelete(responseLogin.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным email")
    public void userLoginCheckUnValidEmail() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseLogin = deserialize(getUserLogin(email + "/", password, 401),
                UserResponseModel.class);

        Assert.assertFalse(responseLogin.success);
        requestDelete(responseCreate.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным password")
    public void userLoginCheckUnValidPassword() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseLogin = deserialize(getUserLogin(email, password + "/", 401),
                UserResponseModel.class);

        Assert.assertFalse(responseLogin.success);
        requestDelete(responseCreate.getAccessToken());
    }
}