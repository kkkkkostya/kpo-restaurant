package authentication.factories;

import authentication.users.User;

public interface UserFactory {
    User createUser(String name, String password);
}
