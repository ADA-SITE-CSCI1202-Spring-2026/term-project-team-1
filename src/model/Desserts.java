package src.model;

public class Desserts extends MenuItem {
    private static final String[] NAMES = {"Ice Cream", "Pie"};
    private static final double[] PRICES = {3.99, 3.49};

    public Desserts (String name, double price) {
        super(name, price);
    }

    public static Desserts randomItem() {
        int i = (int) (Math.random() * NAMES.length);
        return new Desserts(NAMES[i], PRICES[i]);
    }

    @Override
    void GetDetails(){
        System.out.println("Dessert: " + name + ", Price: $" + price);
    }

}
