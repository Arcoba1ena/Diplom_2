package test.user.create;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import functions.UserCreateFunctions;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import models.response.UserCreateResponseModel;

import static api.UserDeleteApi.requestDelete;

@RunWith(Parameterized.class)
public class UserCreateNegativeTest extends UserCreateFunctions {

    @Before
    public void domain() {
        setUp();
    }

    private final String name;
    private final String email;
    private final String password;

    public UserCreateNegativeTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"nameTestUser", "nickNameUser@yandex.ru", "testUser!P@sw0rd"}
        };
    }

    Gson gson = new Gson();
    UserCreateResponseModel responseModel;

    @Test
    @DisplayName("Создание пользователя - проверка [дубликата пользователя]")
    public void userCreateCheckDuplicate() {
        responseModel = gson.fromJson(
                getUserCreate(name, email, password, 200), UserCreateResponseModel.class);
        Assert.assertTrue(getUserCreate(name, email, password, 403).contains("User already exists"));
        requestDelete(responseModel.getAccessToken());
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля name")
    public void userCreateCheckRequiredName() {
        responseModel = gson.fromJson(getUserCreate(null, email, password, 403),
                UserCreateResponseModel.class);
        Assert.assertFalse(responseModel.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля email")
    public void userCreateCheckRequiredEmail() {
        responseModel = gson.fromJson(getUserCreate(name, null, password, 403),
                UserCreateResponseModel.class);
        Assert.assertFalse(responseModel.success);
    }

    @Test
    @DisplayName("Создание пользователя - проверка обязательности поля password")
    public void userCreateCheckRequiredPassword() {
        responseModel = gson.fromJson(getUserCreate(name, email, null, 403),
                UserCreateResponseModel.class);
        Assert.assertFalse(responseModel.success);
    }
}