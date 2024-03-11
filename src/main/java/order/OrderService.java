package order;

import data.OrderDataBase;
import data.RestaurantStats;
import enums.PaymentStatus;
import food.RestaurantMenu;
import utils.DishUtil;
import utils.OrderUtil;

import java.util.Objects;

public class OrderService {
    public void create() {
        System.out.println("Введите информацию для создания нового заказа");

        Order order = OrderUtil.inputOrder();
        if (order.getDishes().isEmpty()) {
            System.out.println("Нужно добавить хотя бы одно блюда в заказ\n");
            return;
        }

        System.out.println();
        System.out.println("Заказ был успешно создан:");
        order.getReadyState().showInfo();

        OrderDataBase.add(order);
    }

    public void pay() {

        System.out.println("Введите инфорацию о заказе для оплаты: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDataBase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка! Такого заказа нет");
            return;
        } else if (order.getPaymentStatus() == PaymentStatus.PAID) {
            System.out.println("Ваше Заказ Id " + id + " уже оплачен!");
            return;
        }

        order.pay();

        System.out.println("Оплата произошла успешно!");
        RestaurantStats.updateTopDishes();
    }

    public void startOrder() {
        System.out.println("Введите id заказа, который хотите запустить");

        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }
        Order order = OrderDataBase.getOrderById(id);
        order.thread.start();
    }

    public void addDish() {
        System.out.println("Введите информацию о заказе для добавления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDataBase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ Id " + id + " уже готов, нельзя добавить в него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("Ваш Заказ Id " + id + " уже готовится, нельзя добавить в него блюда!");
            return;
        }

        RestaurantMenu.showMenu();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (RestaurantMenu.getDishByName(name) == null) {
            System.out.println("Блюда с таким названием не существует!");
            return;
        } else if (RestaurantMenu.getDishByName(name).getPortions() <= 0) {
            System.out.println("К сожалению, данного блюда сейчас нет в наличии(");
            return;
        }
        RestaurantMenu.getDishByName(name).changePortions(1);
        OrderDataBase.addDish(order, RestaurantMenu.getDishByName(name));
        System.out.println("Ваш заказ успешно обновлён:");
        order.getReadyState().showInfo();
    }

    public void deleteDish() {
        System.out.println("Введите информацию о заказе для удаления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDataBase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ Id " + id + " уже готов, нельзя удалить из него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("Ваш Заказ Id " + id + " уже готовится, нельзя удалить из него блюда!");
            return;
        }

        if (order.getDishes().size() <= 1) {
            System.out.println("В заказе только одно блюдо!\nВместо удаления блюда, можно удалить заказ!");
            return;
        }

        order.getReadyState().showInfo();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (RestaurantMenu.getDishByName(name) == null) {
            System.out.println("Блюда с таким названием не существует!");
            return;
        } else if (!order.getDishes().contains(RestaurantMenu.getDishByName(name))) {
            System.out.println("Блюда с таким названием нету в Заказе Id " + id + "!");
            return;
        }
        RestaurantMenu.getDishByName(name).changePortions(-1);
        OrderDataBase.deleteDish(order, RestaurantMenu.getDishByName(name));
        System.out.println("Ваш заказ успешно обновлён:");
        order.getReadyState().showInfo();
    }

    public void cancel() {
        System.out.println("Введите информацию о заказе для отмены: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDataBase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ Id " + id + " уже готов, его нельзя отменить!");
            return;
        }

        OrderDataBase.delete(order);
        System.out.println("Отмена заказа произошла успешно!");
    }
}
