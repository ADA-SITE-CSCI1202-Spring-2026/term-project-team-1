package model;

public class Beverages extends MenuItem {
    private static final String[] NAMES = {"Soda", "Water", "Juice"};
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
}
