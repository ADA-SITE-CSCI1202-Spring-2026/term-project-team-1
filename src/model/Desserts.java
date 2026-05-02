package model;

import java.util.HashMap;
import java.util.Map;

public class Desserts extends MenuItem {
    private static final String[] NAMES = {"Ice Cream", "Cheesecake"};
    private static final double[] PRICES = {3.99, 6.99};

    public Desserts (String name, double price) {
        super(name, price);
    }

    public static Desserts randomItem() {
        int i = (int) (Math.random() * NAMES.length);
        return new Desserts(NAMES[i], PRICES[i]);
    }

    @Override
    public String getDetails() {
        return "Dessert: " + name + " ($" + price + ")";
    }

    @Override
    public Map<Ingredient, Integer> getRequiredIngredients() {
    Map<Ingredient, Integer> required = new HashMap<>();

    if (name.equals("Cheesecake")) {
        required.put(Ingredient.CHEESE, 2);
        required.put(Ingredient.CAKE, 1);
    } else if (name.equals("Ice Cream")) {
        required.put(Ingredient.ICE_CREAM, 1);
    }

    return required;
    }
}
