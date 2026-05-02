package model;

import java.util.HashMap;
import java.util.Map;

public class Beverages extends MenuItem {
    private static final String[] NAMES = {"Milkshake", "Water", "Juice"};
    private static final double[] PRICES = {2.99, 1.99, 3.49};

    public Beverages(String name, double price) {
        super(name, price);
    }

    public static Beverages randomItem() {
        int i = (int) (Math.random() * NAMES.length);
        return new Beverages(NAMES[i], PRICES[i]);
    }

    @Override
    public String getDetails() {
        return "Beverage: " + name + " ($" + price + ")";
    }

    @Override
    public Map<Ingredient, Integer> getRequiredIngredients() {
    Map<Ingredient, Integer> required = new HashMap<>();

    if (name.equals("Milkshake")) {
        required.put(Ingredient.ICE_CREAM, 1);
        required.put(Ingredient.MILK, 1);
    } else if (name.equals("Water")) {
        required.put(Ingredient.WATER, 1);
    } else if (name.equals("Juice")) {
        required.put(Ingredient.JUICE, 1);
    }

    return required;
    }
}

