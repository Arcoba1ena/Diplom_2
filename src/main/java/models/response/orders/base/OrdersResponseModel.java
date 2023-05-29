package models.response.orders.base;

import models.response.orders.base.OrdersModel;

public class OrdersResponseModel {
    public boolean success;
    public String name;
    public OrdersModel order;

    public OrdersResponseModel(boolean success, String name, OrdersModel order) {
        this.success = success;
        this.name = name;
        this.order = order;
    }

    public OrdersResponseModel() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrdersModel getOrder() {
        return order;
    }

    public void setOrder(OrdersModel order) {
        this.order = order;
    }
}