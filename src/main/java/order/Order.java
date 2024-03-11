package order;

import enums.PaymentStatus;
import food.Dish;
import order.states.AcceptedState;
import order.states.OrderState;
import data.OrderDataBase;
import data.RestaurantStats;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static enums.PaymentStatus.NOTPAID;
import static enums.PaymentStatus.PAID;

public class Order {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("dishes")
    private List<Dish> dishes;
    @JsonProperty("totalPrice")
    private double totalPrice;
    private PaymentStatus paymentStatus;
    @JsonProperty("readyStatus")
    private OrderState readyState;
    @JsonProperty("CookingTime)")
    private int totalCookingTime;

    private final Lock lock = new ReentrantLock();
    Thread thread = new Thread(this::startCooking);

    public Order() {
        id = OrderDataBase.getAll().size() + 1;
        dishes = new ArrayList<Dish>();
        totalPrice = 0;
        paymentStatus = NOTPAID;
        readyState = new AcceptedState(this);
        totalCookingTime = 0;
    }

    public Order(@JsonProperty("Id") int id, @JsonProperty("dishes") List<Dish> dishes, @JsonProperty("totalPrice") double totalPrice, @JsonProperty("readyStatus") OrderState readyState) {
        this.id = id;
        this.dishes = dishes;
        this.totalPrice = totalPrice;
        this.readyState = readyState;
        this.paymentStatus = NOTPAID;
        computeTotalCookingTime();
    }

    public int getId() {
        return id;
    }

    public OrderState getReadyState() {
        return readyState;
    }

    public void changeState(OrderState state) {
        this.readyState = state;
    }

    public List<Dish> getDishes() {
        return dishes;
    }


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void pay() {
        paymentStatus = PAID;
        RestaurantStats.updateRevenue(totalPrice);
    }

    public void add(Dish dish) {
        dishes.add(dish);
        updateTotalPrice(dish);
        computeTotalCookingTime();
        for (Dish d : dishes) {
            d.updateSelled(1);
        }
    }

    public void delete(Dish dish) {
        dishes.remove(dish);

        computeTotalCookingTime();
        computeTotalPrice();

        readyState.getAccepted();
    }

    @JsonProperty("readyState")
    public String getReadyStateString() {
        return readyState.getReadyState();
    }

    public void updateTotalPrice(Dish dish) {
        totalPrice += dish.getPrice();
    }

    public void computeTotalCookingTime() {
        this.totalCookingTime = 0;
        for (Dish item : dishes) {
            this.totalCookingTime += item.getCookingTime();
        }
    }

    public void computeTotalPrice() {
        this.totalPrice = 0;
        for (Dish item : dishes) {
            this.totalPrice += item.getPrice();
        }
    }

    public void startCooking() {
        try {
            Thread.sleep(totalCookingTime / 2);
            readyState.getProcessed();

            Thread.sleep(totalCookingTime / 2);
            readyState.getReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
