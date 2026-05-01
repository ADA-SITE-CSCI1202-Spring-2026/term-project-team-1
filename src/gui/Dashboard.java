package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
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

    @FXML
    public void initialize() {
        startTimer();
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
}