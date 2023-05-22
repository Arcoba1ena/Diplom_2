package test.orders;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import functions.user.UserCreateFunctions;
import io.qameta.allure.junit4.DisplayName;
import functions.orders.OrdersCreateFunctions;
import models.response.user.UserResponseModel;
import org.junit.runners.Parameterized.Parameters;
import models.response.orders.base.OrdersResponseModel;
import models.response.orders.auth.OrdersResponseAuthModel;

import static functions.Util.*;
import static functions.user.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class OrdersCreateTest extends OrdersCreateFunctions {

    @Before
    public void domain() {
        apiEndPoint();
    }

    private final String name;
    private final String email;
    private final String password;
    private final String ingredientMain;
    private final String ingredientSauce;

    public OrdersCreateTest(String name, String email, String password, String ingredientMain, String ingredientSauce) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.ingredientMain = ingredientMain;
        this.ingredientSauce = ingredientSauce;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2},{3},{4}")
    public static Object[][] getTestData() {
        return new Object[][]{
            {"testsUser", "testUser@yandex.ru", "uniqueP@sw0rd", "61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa76"}
        };
    }

    private UserResponseModel responseUserCreate;
    private OrdersResponseAuthModel responseOrdersAuth;

    UserCreateFunctions userCreate = new UserCreateFunctions();

    public ArrayList<String> addIngredient() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(ingredientMain);
        ingredients.add(ingredientSauce);
        return ingredients;
    }

    public void getUserCreate() {
        responseUserCreate = deserialize(userCreate.getUserCreate(name, email, password, 200),
                UserResponseModel.class);
    }

    @Test
    @DisplayName("Создание заказа - без авторизации")
    public void ordersCreateWithOutAuth() {
        OrdersResponseModel responseOrdersCreate = deserialize(getOrdersCreate(addIngredient(), null,200),
                OrdersResponseModel.class);

        Assert.assertEquals("Spicy минеральный бургер", responseOrdersCreate.name);
    }

    @Test
    @DisplayName("Создание заказа - c авторизацией, проверка пля [ingredients.name]")
    public void ordersCreateCheckIngredients() {
        getUserCreate();
        responseOrdersAuth = deserialize(getOrdersCreate(addIngredient(), responseUserCreate.getAccessToken(),200),
                OrdersResponseAuthModel.class);

        getUserDelete(responseUserCreate.getAccessToken());
        Assert.assertEquals("Соус Spicy-X", responseOrdersAuth.order.ingredients.get(0).name);
    }

    @Test
    @DisplayName("Создание заказа - c авторизацией, проверка поля [order.name]")
    public void ordersCreateCheckOrder() {
        getUserCreate();
        responseOrdersAuth = deserialize(getOrdersCreate(addIngredient(), responseUserCreate.getAccessToken(),200),
                OrdersResponseAuthModel.class);

        getUserDelete(responseUserCreate.getAccessToken());
        Assert.assertEquals("Spicy минеральный бургер", responseOrdersAuth.order.name);
    }

    @Test
    @DisplayName("Создание заказа - без ингредиентов")
    public void ordersCreateWithOutParams() {
        Assert.assertTrue(getOrdersCreate(new ArrayList<>(), null, 400)
                .contains("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа - с неверным хешем ингредиентов")
    public void ordersCreateUnValidHash() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(ingredientMain + "/");
        Assert.assertTrue(getOrdersCreate(ingredients, null, 500).contains("Internal Server Error"));
    }
}