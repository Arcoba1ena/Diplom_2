package models.response.orders.auth;

import java.util.ArrayList;

public class OrdersGetResponseModel {
    public boolean success;
    public ArrayList<OrdersGetModel> orders;

    public OrdersGetResponseModel(boolean success, ArrayList<OrdersGetModel> orders) {
        this.success = success;
        this.orders = orders;
    }

    public OrdersGetResponseModel() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<OrdersGetModel> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrdersGetModel> orders) {
        this.orders = orders;
    }
}