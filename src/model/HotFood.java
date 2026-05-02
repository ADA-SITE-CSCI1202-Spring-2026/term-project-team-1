package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HotFood extends MenuItem {
    private static final String[] NAMES = {"Burger", "Fries", "Salad"};
    private static final double[] PRICES = {6.99, 2.99, 4.99};

    public HotFood(String name, double price) {
        super(name, price);
    }

    public static HotFood randomItem() {
        Random rand = new Random();
        int i = rand.nextInt(NAMES.length);
        return new HotFood(NAMES[i], PRICES[i]);
    }
    
    @Override
    public String getDetails() {
        return "Hot Food: " + name + " ($" + price + ")";
    }

    @Override
    public Map<Ingredient, Integer> getRequiredIngredients() {
    Map<Ingredient, Integer> required = new HashMap<>();

    if (name.equals("Burger")) {
        required.put(Ingredient.BUN, 1);
        required.put(Ingredient.PATTY, 1);
        required.put(Ingredient.LETTUCE, 1);
        required.put(Ingredient.TOMATO, 1);
        required.put(Ingredient.CHEESE, 1);
    } else if (name.equals("Salad")) {
        required.put(Ingredient.LETTUCE, 1);
        required.put(Ingredient.TOMATO, 1);
        required.put(Ingredient.CUCUMBER, 1);
    } else if (name.equals("Fries")) {
        required.put(Ingredient.POTATO, 2);
    }

    return required;
    }
}
