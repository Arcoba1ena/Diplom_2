package test.user.create;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.restassured.response.Response;
import org.junit.runners.Parameterized.Parameters;

import functions.user.UserCreateFunctions;
import io.qameta.allure.junit4.DisplayName;
import models.response.user.UserResponseModel;

import static functions.Util.checkStatusCode;
import static functions.Util.deserialize;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class UserCreateErrorTest extends UserCreateFunctions {

    @Before
    public void domain() {
        apiEndPoint();
    }

    private final String name;
    private final String email;
    private final String password;

    public UserCreateErrorTest(String name, String email, String password) {
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

    private UserResponseModel responseModel;

    @Test
    @DisplayName("Создание пользователя - проверка [дубликата пользователя]")
    public void userCreateCheckDuplicate() {
        Response response = getUserCreate(name, email, password);
        responseModel = deserialize(response.getBody().asString(), UserResponseModel.class);
        checkStatusCode(response,200);

        Response responseErr = getUserCreate(name, email, password);
        UserResponseModel responseError = deserialize(responseErr.getBody().asString(), UserResponseModel.class);
        checkStatusCode(responseErr,403);

        getUserDelete(responseModel.getAccessToken());
        Assert.assertFalse(responseError.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля [name]")
    public void userCreateCheckRequiredName() {
        Response response = getUserCreate(null, email, password);
        responseModel = deserialize(response.getBody().asString(), UserResponseModel.class);
        checkStatusCode(response,403);
        Assert.assertFalse(responseModel.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля [email]")
    public void userCreateCheckRequiredEmail() {
        Response response = getUserCreate(name, null, password);
        responseModel = deserialize(response.getBody().asString(), UserResponseModel.class);
        checkStatusCode(response,403);
        Assert.assertFalse(responseModel.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля [password]")
    public void userCreateCheckRequiredPassword() {
        Response response = getUserCreate(name, email, null);
        responseModel = deserialize(response.getBody().asString(), UserResponseModel.class);
        checkStatusCode(response,403);
        Assert.assertFalse(responseModel.success);
    }
}