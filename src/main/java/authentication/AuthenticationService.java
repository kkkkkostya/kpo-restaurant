package authentication;

import authentication.factories.AdminFactoty;
import authentication.factories.UserFactory;
import authentication.factories.VisitorFactory;
import authentication.users.User;
import controllers.AdminController;
import controllers.Controller;
import controllers.VisitorController;
import data.UserDataBase;
import enums.InputMode;
import enums.UserMode;
import utils.UserUtil;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationService {
    public boolean registerUser(UserMode mode) {
        UserFactory factory;
        System.out.println("Регистрация пользователя:");
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя или 0 чтобы выйти: ", "Имя пользователя введено некорректно!\n" +
                        "Имя пользователя должно состоять не менее чем из 3-х латинских букв!",
                InputMode.USERNAME
        );
        if (username.isEmpty())
            return false;
        if (UserDataBase.checkUser(username)) {
            System.out.println("Польователь с таким именем уже существует!");
            return false;
        }

        String password = UserUtil.handleInfoInput("Введите пароль или 0 чтобы выйти: ",
                "Пароль введен некорректно!\n" +
                        "Пароль должно содержать не менее 6-х латинских букв и не менее одной цифры!",
                InputMode.PASSWORD);

        if (password.isEmpty())
            return false;
        String hashPass = BCrypt.hashpw(password, BCrypt.gensalt());


        if (mode.equals(UserMode.ADMIN))
            factory = new AdminFactoty();
        else
            factory = new VisitorFactory();
        UserDataBase.addUser(factory.createUser(username, hashPass));
        System.out.println("Пользователь успешно создан");

        return true;
    }

    public Controller userAuthentication() {
        System.out.println("Аунтефикации пользователя:");
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя: ",
                "Имя пользователя введено некорректно!\n" +
                        "Имя пользователя должно состоять не менее чем из 3-х латинских букв!", InputMode.USERNAME);
        if (username.isEmpty()) {
            return null;
        }

        if (!UserDataBase.checkUser(username)) {
            System.out.println("Польователь с таким именем не существует!");
            return null;
        }

        String password = UserUtil.handleInfoInput("Введите пароль: ",
                "Пароль введен некорректно!\n" +
                        "Пароль должно содержать не менее 6-х латинских букв и не менее одной цифры!",
                InputMode.PASSWORD);

        User user = UserDataBase.getUserByName(username);
        if (user != null && BCrypt.checkpw(password, user.getUserPassword())) {
            System.out.println("Вход в систему под ролью " + user.getUserType() + " успешно произведен! \n\n" +
                    "С возвращением " + username + "\n");
            if (user.getUserType().equals("ADMIN"))
                return new AdminController();
            else
                return new VisitorController();
        }
        System.out.println("Неверно введен пароль! Повторите попытку или зарегистрируйте новго пользователя");
        return null;
    }
}
