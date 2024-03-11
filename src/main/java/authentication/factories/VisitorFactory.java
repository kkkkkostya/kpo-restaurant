package authentication.factories;

import authentication.users.User;
import authentication.users.Visitor;

public class VisitorFactory implements UserFactory {
    @Override
    public User createUser(String name, String password) {
        return new Visitor(name, password);
    }
}
