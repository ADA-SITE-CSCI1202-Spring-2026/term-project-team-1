package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.HashMap;

public class GameState implements Serializable {
    private static final long serialVersionUID=1L;
    public static final String SAVE_FILE = "savegame.txt";

    private ArrayDeque<Order> orders;
    private HashMap<Ingredient, Integer> ingredients;
    private double totalBudget;

    public GameState(ArrayDeque<Order> orders, HashMap<Ingredient, Integer> ingredients, double totalBudget){
        this.orders = new ArrayDeque<>(orders);
        this.ingredients = new HashMap<>(ingredients);
        this.totalBudget = totalBudget;
    }

    public ArrayDeque<Order> getOrders(){
        return orders;
    }

    public HashMap<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void saveGame(File saveFile) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            writer.write("budget=" + totalBudget);
            writer.newLine();

            for (var entry : ingredients.entrySet()) {
                writer.write("ingredient=" + entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }

            int orderIndex = 0;
            for (Order order : orders) {
                for (MenuItem item : order.getItems()) {
                    writer.write(
                        "orderItem=" + orderIndex + ","
                            + item.getClass().getSimpleName() + ","
                            + item.getName() + ","
                            + item.getPrice()
                    );
                    writer.newLine();
                }

                orderIndex++;
            }
        }
    }

    public static GameState loadGame(File saveFile) throws IOException{
        double totalBudget = 0;
        HashMap<Ingredient, Integer> ingredients = new HashMap<>();
        ArrayDeque<Order> orders = new ArrayDeque<>();
        HashMap<Integer, Order> orderMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("budget=")) {
                    totalBudget = Double.parseDouble(line.substring("budget=".length()));
                } else if (line.startsWith("ingredient=")) {
                    String[] parts = line.substring("ingredient=".length()).split(",");
                    ingredients.put(Ingredient.valueOf(parts[0]), Integer.parseInt(parts[1]));
                } else if (line.startsWith("orderItem=")) {
                    String[] parts = line.substring("orderItem=".length()).split(",");
                    int orderIndex = Integer.parseInt(parts[0]);
                    MenuItem item = createMenuItem(parts[1], parts[2], Double.parseDouble(parts[3]));
                    Order order = orderMap.computeIfAbsent(orderIndex, key -> new Order());
                    order.addItem(item);
                }
            }
        }

        for (int i = 0; i < orderMap.size(); i++) {
            orders.add(orderMap.get(i));
        }

        return new GameState(orders, ingredients, totalBudget);
    }

    private static MenuItem createMenuItem(String type, String name, double price) {
        if (type.equals("HotFood")) {
            return new HotFood(name, price);
        } else if (type.equals("Beverages")) {
            return new Beverages(name, price);
        } else if (type.equals("Desserts")) {
            return new Desserts(name, price);
        }

        throw new IllegalArgumentException("Unknown menu item type: " + type);
    }
}
