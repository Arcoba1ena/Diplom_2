package functions.orders;

import api.orders.OrdersGetApi;
import io.restassured.response.Response;

public class OrdersGetFunctions extends OrdersGetApi {
    public Response getOrders(String token) {
        return token == null ? requestOrdersGet() : requestOrdersGet(token);
    }
}