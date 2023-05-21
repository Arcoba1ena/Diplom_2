package models.request.order;

import java.util.ArrayList;

public class OrdersCreateRequestModel {

    public ArrayList<String> ingredients;

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public OrdersCreateRequestModel(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public OrdersCreateRequestModel() {
    }
}