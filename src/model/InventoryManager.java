package model;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<Ingredient, Integer> ingredients;
    private double totalBudget;

    public InventoryManager(double totalBudget) {
        this.ingredients = new HashMap<>();
        this.totalBudget = totalBudget;

        for (Ingredient ingredient : Ingredient.values()) {
            ingredients.put(ingredient, 10);
        }
    }

    public int getAmount(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0);
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public HashMap<Ingredient, Integer> getIngredientsCopy() {
        return new HashMap<>(ingredients);
    }

    public void loadState(HashMap<Ingredient, Integer> ingredients, double totalBudget) {
        this.ingredients = new HashMap<>(ingredients);
        this.totalBudget = totalBudget;
    }

    public boolean buyIngredient(Ingredient ingredient) {
        double price = ingredient.getIngredientPrice();

        if (totalBudget < price) {
            return false;
        }

        ingredients.put(ingredient, getAmount(ingredient) + 1);
        totalBudget -= price;

        return true;
    }

    public boolean hasEnough(Order order) {
        HashMap<Ingredient, Integer> totalNeeded = new HashMap<>();
        
        for (MenuItem item : order.getItems()) {
            for (var entry : item.getRequiredIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                int amount = entry.getValue();
                totalNeeded.put(
                    ingredient,
                    totalNeeded.getOrDefault(ingredient, 0) + amount
                );
            }
        }

        for (var entry : totalNeeded.entrySet()) {
            if (getAmount(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void deductIngredients(Order order) {
        for (MenuItem item : order.getItems()) {
            for (var entry : item.getRequiredIngredients().entrySet()) {
                Ingredient ingredient = entry.getKey();
                int amount = entry.getValue();

                ingredients.put(
                    ingredient,
                    ingredients.get(ingredient) - amount
                );
            }
        }
    }

    public boolean cookOrder(Order order) {
        if (!hasEnough(order)) {
            return false;
        }

        deductIngredients(order);
        totalBudget += order.getTotalPrice();

        return true;
    }
}
