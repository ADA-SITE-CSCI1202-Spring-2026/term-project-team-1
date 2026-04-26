package src.gui.Panels;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RestockPanel extends VBox {

    private ComboBox<String> ingredientDropdown;
    private Button buyButton;

    public RestockPanel() {

        Label title = new Label("Restock Panel");
        ingredientDropdown = new ComboBox<>();
        ingredientDropdown.getItems().addAll("Buns", "Patties", "Vegetables");
        buyButton = new Button("Buy Ingredient");
        buyButton.setOnAction(e -> handleRestock());
        getChildren().addAll(title, ingredientDropdown, buyButton);
    }

    private void handleRestock() {
        String selected = ingredientDropdown.getValue();
        System.out.println("Restocked: " + selected);
    }
}