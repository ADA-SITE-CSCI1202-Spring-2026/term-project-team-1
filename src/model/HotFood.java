package model;

import java.util.Random;

public class HotFood extends MenuItem {
    private static final String[] NAMES = {"Burger", "Fries", "Nuggets"};
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
}
