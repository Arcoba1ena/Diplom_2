package models.response.orders.auth;

public class OrdersOwnerModel {
    public String name;
    public String email;

    public OrdersOwnerModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public OrdersOwnerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}