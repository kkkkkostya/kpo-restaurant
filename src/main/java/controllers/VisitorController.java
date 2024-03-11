package controllers;

import data.OrderDataBase;
import food.DishService;
import food.RestaurantMenu;
import order.OrderService;

import java.util.Scanner;

public class VisitorController implements Controller {
    private OrderService orderService;
    private DishService dishService = new DishService();

    public VisitorController() {
        orderService = new OrderService();
    }

    @Override
    public void run() {
        menu(true);
        handleInput();
    }

    @Override
    public void menu(boolean start) {
        if (start)
            System.out.println("Добро пожаловать в меню посетителя!");
        System.out.println("1. Посмотреть меню");
        System.out.println("2. Посмотреть заказы");
        System.out.println("3. Создать заказ");
        System.out.println("4. Запустить заказ");
        System.out.println("5. Добавить блюдо в заказ");
        System.out.println("6. Убрать блюдо из заказа");
        System.out.println("7. Отменить заказ");
        System.out.println("8. Заплатить за заказ");
        System.out.println("9. Оставить отзыв на блюдо");
        System.out.println("0. Выход");
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("\nВведите число от 0 до 9");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    RestaurantMenu.showMenu();
                    break;
                case "2":
                    OrderDataBase.showInfo();
                    break;
                case "3":
                    orderService.create();
                    break;
                case "4":
                    orderService.startOrder();
                    break;
                case "5":
                    orderService.addDish();
                    break;
                case "6":
                    orderService.deleteDish();
                    break;
                case "7":
                    orderService.cancel();
                    break;
                case "8":
                    orderService.pay();
                    break;
                case "9":
                    dishService.addFeedBack();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный ввод. Нужно выбрать действие от 0 до 9");
            }
            menu(false);
        }
    }
}
