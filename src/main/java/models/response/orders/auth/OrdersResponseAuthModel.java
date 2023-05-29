package models.response.orders.auth;

public class OrdersResponseAuthModel {
    public String name;
    public boolean success;
    public OrdersAuthModel order;

    public OrdersResponseAuthModel(String name, boolean success, OrdersAuthModel order) {
        this.name = name;
        this.success = success;
        this.order = order;
    }

    public OrdersResponseAuthModel() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrdersAuthModel getOrder() {
        return order;
    }

    public void setOrder(OrdersAuthModel order) {
        this.order = order;
    }
}