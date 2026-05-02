package model;

public class DessertStation implements IAppliance {
    @Override
    public String process(MenuItem item) {
        if (item instanceof Desserts) {
            return "Dessert station prepared: " + item.getName();
        }

        return null;
    }
}
