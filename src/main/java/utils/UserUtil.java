package utils;

import enums.InputMode;

import java.util.Scanner;

public class UserUtil {
    public static String handleInfoInput(String inputMessage, String errorMessage, InputMode mode) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println(inputMessage);
            input = scanner.nextLine();

            if (input.equals("0")) return "";

            if ((mode == InputMode.PASSWORD && checkPassword(input)) | (mode == InputMode.USERNAME && checkUsername(input))) {
                return input;
            } else {
                System.out.println(errorMessage);
                System.out.println("Повторите попытку или выйдите в меню, введя 0");
            }
        }
    }

    private static boolean checkPassword(String input) {
        return input.matches("(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])(?=.*\\d).*");
    }

    private static boolean checkUsername(String input) {
        return input.matches(".*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*");
    }
}
