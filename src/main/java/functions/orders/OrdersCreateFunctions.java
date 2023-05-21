package functions.orders;

import java.util.ArrayList;
import api.orders.OrdersCreateApi;
import models.request.order.OrdersCreateRequestModel;

public class OrdersCreateFunctions extends OrdersCreateApi {
    public String getOrdersCreate(ArrayList<String> ingredients, String token, Integer code) {
        OrdersCreateRequestModel requestModel = new OrdersCreateRequestModel(ingredients);
        return token == null ? requestOrdersCreate(requestModel, code) : requestOrdersCreate(requestModel, token, code);
    }
}