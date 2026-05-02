package model;

public enum Ingredient {
    BUN(1.00), PATTY(1.50), TOMATO(1.25), LETTUCE(1.25), CUCUMBER(2.00), POTATO(2.25), CHEESE(3.00), MILK(2.50),
    WATER(1.00), JUICE(1.75), CAKE(4.00), ICE_CREAM(2.75);

    private double ingredientPrice;

    Ingredient(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public double getIngredientPrice() {
        return ingredientPrice;
    }
}
