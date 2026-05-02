package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import model.*;

import java.util.*;

public class Dashboard {

    @FXML
    private ListView<String> orderListView;

    @FXML
    private ListView<String> logListView;

    private Deque<Order> orderQueue = new ArrayDeque<>();
    private Random rand = new Random();
    private List<IAppliance> appliances = List.of(
        new Grill(),
        new DrinkDispenser(),
        new DessertStation()
    );

    @FXML 
    private Button cookNextOrderButton;

    @FXML
    private ComboBox<Ingredient> ingredientComboBox;

    @FXML
    private Button buyIngredientButton;

    @FXML
    private Label selectedIngredientAmountLabel;

    @FXML
    private Label budgetLabel;

    private InventoryManager inventoryManager = new InventoryManager(100);

    @FXML
    public void initialize() {
        startTimer();
        setupRestockPanel();

        cookNextOrderButton.setOnAction(e -> {
        Order order = orderQueue.poll();

        if (order == null) {
            logListView.getItems().add("No orders to cook.");
            return;
        }

        boolean cooked = inventoryManager.cookOrder(order);

        if (cooked) {
            processOrderWithAppliances(order);
            logListView.getItems().add("Cooked order: $" + order.getTotalPrice());
        } else {
            logListView.getItems().add("Rejected order: not enough ingredients.");
        }

        //setupCookButton();
        updateInventoryUI();
    });
    }

    private void processOrderWithAppliances(Order order) {
        for (MenuItem item : order.getItems()) {
            for (IAppliance appliance : appliances) {
                String result = appliance.process(item);

                if (result != null) {
                    logListView.getItems().add(result);
                    break;
                }
            }
        }
    }

    private void setupRestockPanel() {
        ingredientComboBox.getItems().addAll(Ingredient.values());
        ingredientComboBox.getSelectionModel().selectFirst();

        ingredientComboBox.setOnAction(e -> updateInventoryUI());
        buyIngredientButton.setOnAction(e -> buySelectedIngredient());

        updateInventoryUI();
    }

    private void buySelectedIngredient() {
        Ingredient ingredient = ingredientComboBox.getValue();

        if (ingredient == null) {
            logListView.getItems().add("Select an ingredient to buy.");
            return;
        }

        boolean bought = inventoryManager.buyIngredient(ingredient);

        if (bought) {
            logListView.getItems().add("Bought ingredient: " + ingredient);
        } else {
            logListView.getItems().add("Not enough cash to buy: " + ingredient);
        }

        updateInventoryUI();
    }

    private void startTimer() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(3), e -> generateOrder())
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void generateOrder() {
        Order order = new Order();

        int itemCount = rand.nextInt(3) + 1;

        for (int i = 0; i < itemCount; i++) {
            int type = rand.nextInt(3);

            if (type == 0) {
                order.addItem(HotFood.randomItem());
            } else if (type == 1) {
                order.addItem(Beverages.randomItem());
            } else {
                order.addItem(Desserts.randomItem());
            }
        }

        orderQueue.add(order);

        updateUI(order);
    }

    private void updateUI(Order order) {
        orderListView.getItems().add(order.toString());

        logListView.getItems().add(
            "New order added. Items: " + order.getItemCount()
        );
    }

    private void updateInventoryUI() {
        Ingredient ingredient = ingredientComboBox.getValue();

        if (ingredient != null) {
            selectedIngredientAmountLabel.setText("In stock: " + inventoryManager.getAmount(ingredient));
        }

        budgetLabel.setText(String.format("Cash: $%.2f", inventoryManager.getTotalBudget()));
    }
}
