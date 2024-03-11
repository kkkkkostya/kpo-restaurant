package food;

import enums.UpdateMode;
import utils.DishUtil;

import java.util.Scanner;

public class DishService {

    public void createDish() {
        String name = DishUtil.inputName();
        if (name.isEmpty()) {
            return;
        } else if (RestaurantMenu.getDishByName(name) != null) {
            System.out.println("Блюдо с таким названием уже существует!");
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == -1) {
            return;
        }

        int prepareTime = DishUtil.inputIntNumber("Введите время приготовления блюда(в мин): ",
                "Время приготовления введено некорректно");
        if (prepareTime == -1) {
            return;
        }

        Dish dish = new Dish(name, price, prepareTime);

        System.out.println("Блюдо " + name + " было успешно добавлено в меню!");
        System.out.println();
        RestaurantMenu.addDish(dish);
    }

    public void deleteDish() {
        String name = DishUtil.inputName();
        if (name.isEmpty()) {
            return;
        } else if (RestaurantMenu.getDishByName(name) == null) {
            System.out.println("Блюдо с таким названием не существует!");
            return;
        }

        RestaurantMenu.getDishByName(name).setPortions(0);
    }

    public void update(UpdateMode mode) {
        String name = DishUtil.inputName();
        if (name.isEmpty()) {
            return;
        } else if (RestaurantMenu.getDishByName(name) == null) {
            System.out.println("Блюдо с таким названием не существует!");
            return;
        }

        if (mode.equals(UpdateMode.PRICE)) {
            double newPrice = DishUtil.inputPrice();
            if (newPrice == -1)
                return;
            RestaurantMenu.update(name, newPrice);
        } else {
            int newData;
            if (mode == UpdateMode.PORTIONS) {
                newData = DishUtil.inputIntNumber("Введите количество порций: ",
                        "Введно некорректное количество порций");
                if (newData != -1)
                    RestaurantMenu.update(name, newData, UpdateMode.PORTIONS);
            } else {
                newData = DishUtil.inputIntNumber("Введите время приготовления блюда(в мин.):  ",
                        "Введно некорректное время приготовления");
                if (newData != -1)
                    RestaurantMenu.update(name, newData, UpdateMode.COOKING_TIME);
            }
            if (newData == -1)
                return;
        }
        System.out.println("Информация о блюде успешно обновлена!");
    }

    public void addFeedBack() {
        String name = DishUtil.inputName();
        if (name.isEmpty()) {
            return;
        }

        Dish dish = RestaurantMenu.getDishByName(name);
        if (dish == null) {
            System.out.println("Не удалось найти блюдо!");
            return;
        }
        int rate = DishUtil.inputRate();
        if (rate == -1)
            return;

        System.out.println("Введите содержание отзыва:");
        Scanner scanner = new Scanner(System.in);
        String feedback = scanner.nextLine().trim();

        dish.addFeedback(rate, feedback);
        System.out.println("Отзыв на блюдо " + name + " был успешно добавлен!");
    }


}
