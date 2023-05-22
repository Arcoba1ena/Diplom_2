package functions.orders;

import api.orders.OrdersGetApi;

public class OrdersGetFunctions extends OrdersGetApi {
    public String getOrders(String token, Integer code) {
        return token == null ? requestOrdersGet(code) : requestOrdersGet(token, code);
    }
}