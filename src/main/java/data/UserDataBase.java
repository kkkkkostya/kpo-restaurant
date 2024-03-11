package data;

import authentication.users.Admin;
import authentication.users.User;
import authentication.users.Visitor;
import controllers.FileController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDataBase {
    private static List<User> users = new ArrayList<User>();
    private static List<Visitor> visitors = new ArrayList<Visitor>();
    private static List<Admin> admins = new ArrayList<Admin>();
    private static final String filePath = "users.json";

    public static boolean checkUser(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name))
                return true;
        }
        return false;
    }

    public static void addUser(User user) {
        users.add(user);
        if (user.getUserType().equals("ADMIN")) {
            admins.add(new Admin(user.getUserName(), user.getUserPassword()));
            FileController.save(admins, "admins.json");
        } else {
            visitors.add(new Visitor(user.getUserName(), user.getUserPassword()));
            FileController.save(visitors, "visitors.json");
        }
        FileController.save(users, filePath);
    }

    public static User getUserByName(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name))
                return user;
        }
        return null;
    }
}
