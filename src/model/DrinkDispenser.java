package model;

public class DrinkDispenser implements IAppliance {
    @Override
    public String process(MenuItem item) {
        if (item instanceof Beverages) {
            return "Drink dispenser prepared: " + item.getName();
        }

        return null;
    }
}
