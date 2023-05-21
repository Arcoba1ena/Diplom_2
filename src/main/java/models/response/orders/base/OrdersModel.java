package models.response.orders.base;

public class OrdersModel {
    public int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public OrdersModel() {
    }

    public OrdersModel(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}