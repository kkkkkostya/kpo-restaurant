package data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RestStatsData {
    private static double totalRevenue = 0.0;
    private Map<String, Integer> top5Dishes;

    public RestStatsData() {
        totalRevenue = RestaurantStats.getRevenue();
        top5Dishes = RestaurantStats.getTop5Dishes();
    }

    public RestStatsData(@JsonProperty("totalRevenue") double totalRevenue, @JsonProperty("topDishes")
    Map<String, Integer> top5Dishes) {
        this.totalRevenue = totalRevenue;
        this.top5Dishes = top5Dishes;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Map<String, Integer> getTopDishes() {
        return top5Dishes;
    }

    public void setTopDishes(Map<String, Integer> top5Dishes) {
        this.top5Dishes = top5Dishes;
    }
}
