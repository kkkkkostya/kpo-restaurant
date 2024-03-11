package data;

import controllers.FileController;
import food.Dish;
import order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDataBase {
    private static List<Order> orders = new ArrayList<>();
    private final static String filePath = "orders.json";

    public static List<Order> getAll() {
        if (orders == null) {
            orders = new ArrayList<Order>();
        }

        return orders;
    }

    public static void add(Order order) {
        orders.add(order);
        FileController.save(orders, filePath);
    }

    public static Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    public static void addDish(Order order, Dish dish) {
        order.add(dish);

        FileController.save(orders, filePath);
    }

    public static void deleteDish(Order order, Dish dish) {
        order.delete(dish);

        FileController.save(orders, filePath);
    }

    public static void delete(Order order) {
        orders = getAll();
        orders.remove(order);

        FileController.save(orders, filePath);
    }

    public static void showInfo() {
        if (orders.isEmpty()) {
            System.out.println("Созданных заказов пока нет!\n");
            return;
        }

        for (var order : orders) {
            order.getReadyState().showInfo();
        }
    }
}
