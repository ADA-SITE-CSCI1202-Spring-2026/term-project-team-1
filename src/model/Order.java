package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order:\n");
        for (MenuItem item : items) {
            sb.append(item.getDetails()).append("\n");
        }
        
        return sb.toString();
    }
}
