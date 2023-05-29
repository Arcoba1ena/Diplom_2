package functions.orders;

import java.util.ArrayList;
import api.orders.OrdersCreateApi;
import io.restassured.response.Response;
import models.request.order.OrdersCreateRequestModel;

public class OrdersCreateFunctions extends OrdersCreateApi {
    public Response getOrdersCreate(ArrayList<String> ingredients, String token) {
        OrdersCreateRequestModel requestModel = new OrdersCreateRequestModel(ingredients);
        return token == null ? requestOrdersCreate(requestModel) : requestOrdersCreate(requestModel, token);
    }
}