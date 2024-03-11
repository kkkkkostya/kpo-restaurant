package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import controllers.FileController;
import food.Dish;
import food.RestaurantMenu;
import utils.DishUtil;

import java.util.*;

public class RestaurantStats {
    @JsonProperty("totalRevenue")
    private static double totalRevenue = 0.0;
    @JsonProperty("topDishes")
    private static Map<String, Integer> top5Dishes;

    @JsonProperty("totalRevenue")
    public static void updateRevenue(double rev) {
        totalRevenue += rev;
        FileController.saveRestStat("stats.json");
    }

    @JsonProperty("totalRevenue")
    public static double getRevenue() {
        return totalRevenue;
    }

    @JsonProperty("totalRevenue")
    public static void setRevenue(double revenue) {
        RestaurantStats.totalRevenue = revenue;
    }

    @JsonProperty("topDishes")
    public static void updateTopDishes() {
        List<Dish> topDishes = RestaurantMenu.getAllDishes();
        topDishes.sort(Comparator.comparingInt(Dish::getSelled));
        RestaurantStats.top5Dishes = new HashMap<String, Integer>();
        for (int i = 0; i < Math.min(topDishes.size(), 5); i++) {
            RestaurantStats.top5Dishes.put(topDishes.get(i).getName(), topDishes.get(i).getSelled());
        }
        FileController.saveRestStat("stats.json");
    }

    public static Map<String, Integer> getTop5Dishes() {
        if (top5Dishes == null)
            return new HashMap<String, Integer>();
        return top5Dishes;
    }

    public static void setTop5Dishes(Map<String, Integer> top5Dishes) {
        RestaurantStats.top5Dishes = top5Dishes;
    }

    public static void getTopDishes() {
        int n = DishUtil.inputIntNumber("Введите кол-во блюд в топе", "Кол-во должно быть больше 0");
        n = n == -1 ? 10 : n;
        List<Dish> topDishes = RestaurantMenu.getAllDishes();
        if (topDishes.isEmpty()) {
            System.out.println("Доступных блюд пока нет\n");
            return;
        }
        topDishes.sort(Comparator.comparingInt(Dish::getSelled));
        System.out.println("\nТоп самых популярных блюд:");
        for (int i = 0; i < Math.min(topDishes.size(), n); i++) {
            System.out.println((i + 1) + ") " + topDishes.get(i).getName() + " " + topDishes.get(i).getSelled() + " заказов");
        }
        System.out.println();
    }

    public static void show() {
        System.out.println();
        System.out.println("Выручка ресторана: " + totalRevenue + " $");
        System.out.println();
    }
}
