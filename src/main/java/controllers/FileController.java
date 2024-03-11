package controllers;

import authentication.users.Visitor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import authentication.users.Admin;
import data.RestStatsData;
import data.RestaurantStats;
import food.Dish;
import data.UserDataBase;
import food.RestaurantMenu;

public class FileController {
    private static String dataFolderPath = "src/main/java/DataFiles";

    public static <T> void save(List<T> data, String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), data);

            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

    public static <T> void saveRestStat(String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), new RestStatsData());

            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

    public static void uploadStats() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "stats.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            RestStatsData stats = objectMapper.readValue(new File(filePath), RestStatsData.class);

            RestaurantStats.setTop5Dishes(stats.getTopDishes());
            RestaurantStats.setRevenue(stats.getTotalRevenue());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadAdmins() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "admins.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Admin> users = Arrays.asList(objectMapper.readValue(new File(filePath), Admin[].class));

            for (Admin admin : users) {
                UserDataBase.addUser(admin);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadVisitors() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "visitors.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Visitor> users = Arrays.asList(objectMapper.readValue(new File(filePath), Visitor[].class));

            for (Visitor visitor : users) {
                UserDataBase.addUser(visitor);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadDishes(String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Dish> dishes = Arrays.asList(objectMapper.readValue(new File(filePath), Dish[].class));

            for (Dish dish : dishes) {
                dish.showInfo();
                RestaurantMenu.addDish(dish);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
