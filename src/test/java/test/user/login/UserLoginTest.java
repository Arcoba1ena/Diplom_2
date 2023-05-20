package test.user.login;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import functions.UserLoginFunctions;
import functions.UserCreateFunctions;
import org.junit.runners.Parameterized;
import models.response.UserResponseModel;
import io.qameta.allure.junit4.DisplayName;

import static api.UserDeleteApi.requestDelete;

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

    @Parameterized.Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUsers", "testUsers@yandex.ru", "uniqueP@sw0rds"}
        };
    }

    Gson gson = new Gson();
    UserResponseModel loginResponse;
    UserCreateFunctions userCreate = new UserCreateFunctions();

    @Test
    @DisplayName("Авторизация пользователя - проверка под существующим пользователем")
    public void userCreateCheckAuth() {
        userCreate.getUserCreate(name, email, password, 200);
        loginResponse = gson.fromJson(getUserLogin(email, password, 200), UserResponseModel.class);
        Assert.assertTrue(loginResponse.success);
        requestDelete(loginResponse.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным email")
    public void userCreateCheckUnValidEmail() {
        UserResponseModel createResponse = gson.fromJson(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        loginResponse = gson.fromJson(getUserLogin(email + "/", password, 401), UserResponseModel.class);
        Assert.assertFalse(loginResponse.success);
        requestDelete(createResponse.getAccessToken());
    }

    @Test
    @DisplayName("Авторизация пользователя - авторизация с неверным password")
    public void userCreateCheckUnValidPassword() {
        UserResponseModel createResponse = gson.fromJson(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        loginResponse = gson.fromJson(getUserLogin(email, password + "/", 401), UserResponseModel.class);
        Assert.assertFalse(loginResponse.success);
        requestDelete(createResponse.getAccessToken());
    }
}