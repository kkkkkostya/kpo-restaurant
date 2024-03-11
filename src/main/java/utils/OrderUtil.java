package utils;

import food.Dish;
import food.RestaurantMenu;
import order.Order;
import data.OrderDataBase;

import java.util.Scanner;

public class OrderUtil {
    public static Order inputOrder() {
        Order resultOrder = new Order();

        String dishName;
        RestaurantMenu.showMenu();
        System.out.println("Введите название блюда или 0 чтобы выйти");
        while (true) {
            dishName = DishUtil.inputName();
            if (dishName.equals("0")) break;

            Dish dish = RestaurantMenu.getDishByName(dishName);
            if (dish == null) {
                System.out.println("Не удалось найти текущее блюдо.");
                continue;
            }

            if (dish.getPortions() <= 0) {
                System.out.println("Сейчас в ресторане нет данного блюда в наличии");
            } else {
                resultOrder.add(dish);
                dish.setPortions(dish.getPortions() - 1);
                System.out.println("Блюдо успешно добавлено в текущий заказ.");
                break;
            }
        }

        return resultOrder;
    }

    public static int inputOrderById() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            try {
                System.out.print("Введите id заказа: ");
                id = scanner.nextInt();

                if (id == -1) {
                    return 0;
                } else if (id <= 0) {
                    System.out.println("Id заказа должно быть больше нуля.");
                } else if (OrderDataBase.getOrderById(id).equals(null)) {
                    System.out.println("Заказа с таким Id не существует! Введите -1 чтобы выйти");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Id введено некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return id;
    }
}
