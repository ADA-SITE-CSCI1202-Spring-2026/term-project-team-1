/**
 * Represents a hot food item such as a burger or fries.
 * Requires the Grill appliance to process.
 */
package src.model;
public class HotFood extends MenuItem {

    private String mainIngredient;

    public HotFood(String name, int price, String mainIngredient) {
        super(name, price);
        this.mainIngredient = mainIngredient;
    }

    @Override
    public String getRequiredIngredients() {
        return mainIngredient;
    }

    @Override
    public String getRequiredAppliance() {
        return "Grill";
    }
}