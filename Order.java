package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

    private static final long serialVersionUID=1L;
    private List<MenuItem> items;
    private int id;

    public Order() {
        items = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
        StringBuilder sb = new StringBuilder("Order#" + id + ":\n");
        for (MenuItem item : items) {
            sb.append(item.getDetails()).append("\n");
        }
        
        sb.append("Total price: $" + getTotalPrice());
        return sb.toString();
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}
