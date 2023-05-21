package models.response.orders.auth;

import java.util.ArrayList;

public class OrdersAuthModel {
    public String name;
    public String status;
    public OrdersOwnerModel owner;
    public ArrayList<OrdersIngredientModel> ingredients;

    public OrdersAuthModel(String name, String status, OrdersOwnerModel owner,
            ArrayList<OrdersIngredientModel> ingredients) {
        this.name = name;
        this.status = status;
        this.owner = owner;
        this.ingredients = ingredients;
    }

    public OrdersAuthModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrdersOwnerModel getOwner() {
        return owner;
    }

    public void setOwner(OrdersOwnerModel owner) {
        this.owner = owner;
    }

    public ArrayList<OrdersIngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<OrdersIngredientModel> ingredients) {
        this.ingredients = ingredients;
    }
}