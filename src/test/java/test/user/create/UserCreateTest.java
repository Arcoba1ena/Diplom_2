package test.user.create;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import functions.user.UserCreateFunctions;
import models.response.user.UserResponseModel;
import io.qameta.allure.junit4.DisplayName;

import static functions.Util.deserialize;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class UserCreateTest extends UserCreateFunctions {

    @Before
    public void domain() {
        apiEndPoint();
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

    UserResponseModel response;

    @Test
    @DisplayName("Создание пользователя - проверка поля [name]")
    public void userCreateCheckName() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertEquals(name, response.getUser().name);
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [email]")
    public void userCreateCheckEmail() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertEquals(email.toLowerCase(), response.getUser().email.toLowerCase());
    }

    @Test
    @DisplayName("Создание пользователя - проверка поля [success]")
    public void userCreateCheckStatusResult() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertTrue(response.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [accessToken]")
    public void userCreateCheckAccessToken() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertFalse(response.getAccessToken().isEmpty());
    }

    @Test
    @DisplayName("Создание пользователя - проверка содержания [refreshToken]")
    public void userCreateCheckRefreshToken() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertFalse(response.getRefreshToken().isEmpty());
    }

    @Test
    @DisplayName("Создание пользователя - проверка [дубликата пользователя]")
    public void userCreateCheckDuplicate() {
        response = deserialize(getUserCreate(name, email, password, 200),
                UserResponseModel.class);

        Assert.assertTrue(getUserCreate(name, email, password, 403)
                .contains("User already exists"));
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля name")
    public void userCreateCheckRequiredName() {
        response = deserialize(getUserCreate(null, email, password, 403),
                UserResponseModel.class);

        Assert.assertFalse(response.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля email")
    public void userCreateCheckRequiredEmail() {
        response = deserialize(getUserCreate(name, null, password, 403),
                UserResponseModel.class);

        Assert.assertFalse(response.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля password")
    public void userCreateCheckRequiredPassword() {
        response = deserialize(getUserCreate(name, email, null, 403),
                UserResponseModel.class);

        Assert.assertFalse(response.success);
    }

    @After
    public void userDelete() {
        getUserDelete(response.getAccessToken());
    }
}