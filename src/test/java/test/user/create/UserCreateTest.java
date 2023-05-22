package test.user.create;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import functions.user.UserCreateFunctions;
import io.qameta.allure.junit4.DisplayName;
import models.response.user.UserResponseModel;

import static functions.Util.deserialize;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class UserCreateTest extends UserCreateFunctions {

    @Before
    public void domain() {
        apiEndPoint();
        getUserCreate();
    }

    private final String name;
    private final String email;
    private final String password;

    public UserCreateTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUser", "testUser@yandex.ru", "uniqueP@sw0rd"}
        };
    }

    private UserResponseModel response;

    public void getUserCreate() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [name]")
    public void userCreateCheckName() {
        Assert.assertEquals(name, response.getUser().name);
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [email]")
    public void userCreateCheckEmail() {
        Assert.assertEquals(email.toLowerCase(), response.getUser().email.toLowerCase());
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [success]")
    public void userCreateCheckStatusResult() {
        Assert.assertTrue(response.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [accessToken]")
    public void userCreateCheckAccessToken() {
        Assert.assertFalse(response.getAccessToken().isEmpty());
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [refreshToken]")
    public void userCreateCheckRefreshToken() {
        Assert.assertFalse(response.getRefreshToken().isEmpty());
    }

    @After
    public void userDelete() {
        getUserDelete(response.getAccessToken());
    }
}