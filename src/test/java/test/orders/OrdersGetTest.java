package test.orders;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import functions.user.UserCreateFunctions;
import io.qameta.allure.junit4.DisplayName;
import functions.orders.OrdersGetFunctions;
import models.response.user.UserResponseModel;
import functions.orders.OrdersCreateFunctions;
import models.response.orders.auth.OrdersGetResponseModel;

import static functions.Util.deserialize;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class OrdersGetTest extends OrdersGetFunctions {

    @Before
    public void domain() {
        apiEndPoint();
        getCreateUser();
        getCreateOrder();
    }

    private final String name;
    private final String email;
    private final String password;
    private final String ingredient;

    public OrdersGetTest(String name, String email, String password, String ingredient) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ingredient = ingredient;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2},{3}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"testsUser", "testUser@yandex.ru", "uniqueP@sw0rd","61c0c5a71d1f82001bdaaa72"}
        };
    }

    private UserResponseModel responseCreate;
    private OrdersGetResponseModel responseGet;

    UserCreateFunctions userCreate = new UserCreateFunctions();
    OrdersCreateFunctions ordersCreate = new OrdersCreateFunctions();

    public void getCreateUser(){
        responseCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);
    }

    public void getCreateOrder(){
        ordersCreate.getOrdersCreate(ingredients(), responseCreate.getAccessToken(), 200);
    }

    public ArrayList<String> ingredients(){
        ArrayList<String> addiIngredient = new ArrayList<>();
        addiIngredient.add(ingredient);
        return addiIngredient;
    }

    @Test
    @DisplayName("Получение заказа пользователя - пользователь авторизован, проверка поля[success]")
    public void orderGetSuccess() {
        responseGet = deserialize(getOrders(responseCreate.getAccessToken(),200),
                OrdersGetResponseModel.class);
        Assert.assertTrue(responseGet.success);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя - авторизованный пользователь, проверка поля[name]")
    public void orderGetName() {
        responseGet = deserialize(getOrders(responseCreate.getAccessToken(),200),
                OrdersGetResponseModel.class);
        Assert.assertEquals("Spicy бургер", responseGet.orders.get(0).name);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя - авторизованный пользователь, проверка поля[name]")
    public void orderGetStatus() {
        responseGet = deserialize(getOrders(responseCreate.getAccessToken(),200),
                OrdersGetResponseModel.class);
        Assert.assertEquals("done", responseGet.orders.get(0).status);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя - авторизованный пользователь, проверка поля[name]")
    public void orderGetIngredients() {
        responseGet = deserialize(getOrders(responseCreate.getAccessToken(),200),
                OrdersGetResponseModel.class);
        Assert.assertEquals(ingredient, responseGet.orders.get(0).ingredients.get(0));
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя - авторизованный пользователь, проверка поля[name]")
    public void orderGetError() {
        Assert.assertTrue(getOrders(null,401)
                .contains("You should be authorised"));
    }

    @After
    public void userDelete() {
        getUserDelete(responseCreate.getAccessToken());
    }
}