package authentication.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin implements User {
    private String userName;
    private String password;
    @JsonProperty("userType")
    private String userType;

    public Admin(@JsonProperty("userName") String userName, @JsonProperty("password") String password) {
        this.userName = userName;
        this.password = password;
        userType = "ADMIN";
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override @JsonProperty("userType")
    public String getUserType() {
        return userType;
    }

    @Override
    public String getUserPassword() {
        return password;
    }

    @Override @JsonProperty("userType")
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void showUserInfo() {
        System.out.println("Логин администратора: " + userName);
    }
}
