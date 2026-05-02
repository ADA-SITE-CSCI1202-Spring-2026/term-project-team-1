package model;
import java.io.Serializable;
import java.util.Map;

abstract class MenuItem implements Serializable {
    private static final long serialVersionUID=1L;
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public abstract String getDetails();
    public abstract Map<Ingredient, Integer> getRequiredIngredients();

    public double getPrice() {
        return price;
    }
}
