package model;

public class Grill implements IAppliance {
    @Override
    public String process(MenuItem item) {
        if (item instanceof HotFood) {
            return "Grill prepared: " + item.getName();
        }

        return null;
    }
}
