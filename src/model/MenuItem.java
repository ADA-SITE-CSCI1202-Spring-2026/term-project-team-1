package src.model;
/**
 * Abstract base class for all menu items.
 * Each menu item has a name and defines its own resource requirements.
 */
public abstract class MenuItem {

    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract String getRequiredIngredients();

    public abstract String getRequiredAppliance();

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}