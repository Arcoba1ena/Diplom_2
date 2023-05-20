package test.user.create;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import functions.UserCreateFunctions;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import models.response.UserCreateResponseModel;

import static api.UserDeleteApi.requestDelete;

@RunWith(Parameterized.class)
public class UserCreateTest extends UserCreateFunctions {

    @Before
    public void domain() {
        setUp();
    }

    private final String name;
    private final String email;
    private final String password;

    public UserCreateTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUser", "testUser@yandex.ru", "uniqueP@sw0rd"}
        };
    }

    Gson gson = new Gson();
    UserCreateResponseModel responseModel;

    @Test
    @DisplayName("Создание пользователя - проверка поля [name]")
    public void userCreateCheckName() {
        responseModel = gson.fromJson(getUserCreate(name, email, password, 200),
                UserCreateResponseModel.class);
        Assert.assertEquals(name, responseModel.getUser().name);
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [email]")
    public void userCreateCheckEmail() {
        responseModel = gson.fromJson(getUserCreate(name, email, password, 200),
                UserCreateResponseModel.class);
        Assert.assertEquals(email.toLowerCase(), responseModel.getUser().email.toLowerCase());
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [success]")
    public void userCreateCheckStatusResult() {
        responseModel = gson.fromJson(getUserCreate(name, email, password, 200),
                UserCreateResponseModel.class);
        Assert.assertTrue(responseModel.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [accessToken]")
    public void userCreateCheckAccessToken() {
        responseModel = gson.fromJson(getUserCreate(name, email, password, 200),
                UserCreateResponseModel.class);
        Assert.assertFalse(responseModel.getAccessToken().isEmpty());
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [refreshToken]")
    public void userCreateCheckRefreshToken() {
        responseModel = gson.fromJson(getUserCreate(name, email, password, 200),
                UserCreateResponseModel.class);
        Assert.assertFalse(responseModel.getRefreshToken().isEmpty());
    }

    @After
    public void userDelete() {
        requestDelete(responseModel.getAccessToken());
    }
}