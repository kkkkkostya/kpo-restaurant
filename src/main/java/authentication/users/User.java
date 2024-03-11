package authentication.users;

public interface User {
    String getUserName();
    String getUserType();
    String getUserPassword();
    void setUserType(String userType);
    void showUserInfo();
}
