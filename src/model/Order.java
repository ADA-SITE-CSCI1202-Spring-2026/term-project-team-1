package src.model;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Order {
    Deque<Order> orderQueue = new ArrayDeque<>();

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(3), event -> {
            Order order = new Order();
            Random rand = new Random();
            int itemCount = rand.nextInt(3) + 1; // 1–3 items
            for (int i = 0; i < itemCount; i++) {
                int type = rand.nextInt(3);
                if (type == 0) {
                    order.addItem(HotFood.randomItem());
                } else if (type == 1) {
                    order.addItem(Beverage.randomItem());
                } else {
                    order.addItem(Dessert.randomItem());
                }
            }
            orderQueue.add(order);
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

}
