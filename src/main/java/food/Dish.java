package food;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import controllers.FileController;

public class Dish {
    private String name = "";
    private double price = 0.0;
    private int cookingTime = 0;
    private int portions = 1;
    private int selled = 0;
    @JsonProperty("feedbacks")
    private List<Feedback> feedbacks = new ArrayList<Feedback>();


    public Dish(String name, double price, int cookingTime) {
        this.name = name;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    public Dish(@JsonProperty("name") String name, @JsonProperty("price") double price,
                @JsonProperty("preparetime") int cookingTime, @JsonProperty("portions") int portions) {
        this(name, price, cookingTime);
        this.portions = portions;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updateSelled(int value) {
        selled += value;
    }

    public int getSelled() {
        return selled;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getPortions() {
        return this.portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public void changePortions(int portions) {
        this.portions -= portions;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(int rate, String feedback) {

        feedbacks.add(new Feedback(rate, feedback));
        FileController.save(RestaurantMenu.getMenu(), "dishes.json");
    }

    public void showInfo() {
        System.out.println("Блюдо: " + getName());
        System.out.println("Цена: " + getPrice() + " $");
        System.out.println("Время приготовления: " + getCookingTime() + " мин.");
        System.out.println("Количество доступных порций: " + getPortions());
        System.out.println();
    }
}
