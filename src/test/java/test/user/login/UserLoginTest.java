package test.user.login;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import functions.user.UserLoginFunctions;
import functions.user.UserCreateFunctions;
import models.response.user.UserResponseModel;
import io.qameta.allure.junit4.DisplayName;

import static functions.Util.deserialize;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class UserLoginTest extends UserLoginFunctions {

    @Before
    public void domain() {
        apiEndPoint();
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
       responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
               UserResponseModel.class);

        responseLogin = deserialize(getUserLogin(email, password, 200),
                UserResponseModel.class);

        Assert.assertTrue(responseLogin.success);
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным email")
    public void userLoginCheckUnValidEmail() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseLogin = deserialize(getUserLogin(email + "/", password, 401),
                UserResponseModel.class);

        Assert.assertFalse(responseLogin.success);
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным password")
    public void userLoginCheckUnValidPassword() {
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        responseLogin = deserialize(getUserLogin(email, password + "/", 401),
                UserResponseModel.class);

        Assert.assertFalse(responseLogin.success);
    }

    @After
    public void userDelete() {
        getUserDelete(responseCreate.getAccessToken());
    }
}