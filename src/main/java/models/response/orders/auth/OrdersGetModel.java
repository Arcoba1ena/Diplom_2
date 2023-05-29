package models.response.orders.auth;

import java.util.ArrayList;

public class OrdersGetModel {
    public String name;
    public String status;
    public ArrayList<String> ingredients;

    public OrdersGetModel(String name, String status, ArrayList<String> ingredients) {
        this.name = name;
        this.status = status;
        this.ingredients = ingredients;
    }

    public OrdersGetModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}