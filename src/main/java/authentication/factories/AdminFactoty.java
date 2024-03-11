package authentication.factories;

import authentication.users.Admin;
import authentication.users.User;

public class AdminFactoty implements UserFactory {
    @Override
    public User createUser(String name, String password) {
        return new Admin(name, password);
    }
}
