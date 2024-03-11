package utils;

import enums.InputMode;
import food.Dish;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DishUtil {
    public static String inputName() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Введите имя блюда или 0 чтобы выйти:");
            input = scanner.nextLine();

            if (input.equals("0")) return "0";

            if (isCorrectName(input)) {
                return input;
            } else {
                System.out.println("Имя блюда введено неправильно. Оно должно состоить только из латинских букв и " +
                        "цифр\n Если хотите выйти в меню - введите 0");
            }
        }
    }

    private static boolean isCorrectName(String name) {
        return name.matches("^(?=.*[a-zA-Z])\\w*$");
    }

    public static double inputPrice() {
        Scanner scanner = new Scanner(System.in);
        Double input;
        while (true) {
            System.out.println("Введите цену блюда:");
            try {
                input = scanner.nextDouble();
                if (input == -1)
                    return -1;
                else if (input <= 0)
                    System.out.println("Цена блюда должна быть больше нуля");
                else
                    return input;
            } catch (InputMismatchException e) {
                System.out.println("Цена блюда введено неверно. Повторите ввод или нажмите -1 чтобы выйти в меню");
                scanner.nextLine();
            }
        }
    }

    public static int inputIntNumber(String message, String warning) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println(message);
            try {
                input = scanner.nextInt();
                if (input == -1)
                    return -1;
                else if (input <= 0)
                    System.out.println("Введенное число должно быть больше 0");
                else
                    return input;
            } catch (InputMismatchException e) {
                System.out.println(warning + " Повторите ввод или нажмите -1 чтобы выйти в меню");
                scanner.nextLine();
            }
        }
    }

    public static int inputRate() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Введите оценку блюда от 1 до 10 или -1 чтобы выйти:");
            try {
                input = scanner.nextInt();
                if (input == -1)
                    return -1;
                else if (input <= 0 || input > 10)
                    System.out.println("Введенное число должно быть больше 0 и меньше 11");
                else
                    return input;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод! Повторите ввод или нажмите -1 чтобы выйти в меню");
                scanner.nextLine();
            }
        }
    }
}
