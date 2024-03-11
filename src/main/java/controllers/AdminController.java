package controllers;

import enums.UpdateMode;
import food.DishService;
import food.RestaurantMenu;
import data.RestaurantStats;

import java.util.Scanner;

public class AdminController implements Controller {
    private DishService dishService = new DishService();

    @Override
    public void run() {
        menu(true);
        handleInput();
    }

    @Override
    public void menu(boolean start) {
        if (start)
            System.out.println("Добро пожаловать в меню администратора!");
        System.out.println("Выберить одну из возможных функций:");
        System.out.println("1. Просмотр текущего меню");
        System.out.println("2. Добавить блюдо");
        System.out.println("3. Удалить блюдо");
        System.out.println("4. Изменить кол-во для конкретного блюда");
        System.out.println("5. Изменить цену блюда");
        System.out.println("6. Изменить время приготовления блюда");
        System.out.println("7. Посмотреть выручку ресторана");
        System.out.println("8. Посмотреть самые популярные блюда");
        System.out.println("0. Выход");
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("\nВведите число от 0 до 8");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    RestaurantMenu.showMenu();
                    break;
                case "2":
                    dishService.createDish();
                    break;
                case "3":
                    dishService.deleteDish();
                    break;
                case "4":
                    dishService.update(UpdateMode.PORTIONS);
                    break;
                case "5":
                    dishService.update(UpdateMode.PRICE);
                    break;
                case "6":
                    dishService.update(UpdateMode.COOKING_TIME);
                    break;
                case "7":
                    RestaurantStats.show();
                    break;
                case "8":
                    RestaurantStats.getTopDishes();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректная команда. Нужно ввести число от 1 до 4");
                    break;
            }
            menu(false);
        }
    }
}