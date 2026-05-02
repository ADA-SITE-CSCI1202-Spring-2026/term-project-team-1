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
