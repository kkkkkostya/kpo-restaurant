package controllers;

import authentication.AuthenticationService;
import enums.UserMode;

import java.util.Scanner;

public class AuthenticationController implements Controller {
    private AuthenticationService service;

    public AuthenticationController(AuthenticationService authService) {
        service = authService;
    }

    @Override
    public void run() {
        menu(true);
        handleInput();
    }

    @Override
    public void menu(boolean start) {
        if (start)
            System.out.println("Добро пожаловать в ресторан! Очень рады вас видеть:");
        System.out.println("Выберить одну из возможных функций:");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация нового посетителя");
        System.out.println("3. Регистрация нового администратора");
        System.out.println("4. Выход");
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("\nВведите число от 1 до 4");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    Controller cont = service.userAuthentication();
                    if (cont != null)
                        cont.run();
                    break;
                case "2":
                    if (service.registerUser(UserMode.VISITOR))
                        run();
                    else {
                        System.out.println("Регистрация прошла неуспешно(");
                        break;
                    }
                case "3":
                    if (service.registerUser(UserMode.ADMIN))
                        run();
                    else {
                        System.out.println("Регистрация прошла неуспешно(");
                        break;
                    }
                case "4":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Некорректная команда. Нужно ввести число от 1 до 4");
            }
            menu(false);
        }
    }
}

