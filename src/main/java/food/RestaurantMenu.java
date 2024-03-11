package food;

import controllers.FileController;
import enums.UpdateMode;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenu {
    private static List<Dish> dishes = new ArrayList<Dish>();
    private static final String filePath = "dishes.json";

    public static List<Dish> getMenu() {
        return dishes;
    }

    public static List<Dish> getAllDishes() {
        return new ArrayList<>(dishes);
    }

    public static void showMenu() {
        if (dishes.isEmpty())
            System.out.println("Пока что-то в меню нет блюд. Но самое время их добавить!\n");
        else {
            System.out.println("Текущее меню: \n");
            for (Dish d : dishes) {
                d.showInfo();
            }
        }
    }

    public static void addDish(Dish dish) {

        dishes.add(dish);
        FileController.save(dishes, filePath);
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }

    public static void deleteDish(String name) {
        for (int i = 0; i <= dishes.size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.remove(i);
                FileController.save(dishes, filePath);
                break;
            }
        }
    }

    public static void update(String name, double price) {
        for (int i = 0; i <= dishes.size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.get(i).setPrice(price);
                FileController.save(dishes, filePath);
                break;
            }
        }
    }

    public static void update(String name, int newData, UpdateMode mode) {
        for (int i = 0; i <= dishes.size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                if (mode.equals(UpdateMode.COOKING_TIME))
                    dishes.get(i).setCookingTime(newData);
                else
                    dishes.get(i).setPortions(newData);
                FileController.save(dishes, filePath);
                break;
            }
        }
    }
}
