package authentication.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Visitor implements User {
    private String userName;
    private String password;
    private String userType;

    public Visitor(@JsonProperty("userName") String userName,@JsonProperty("password") String password) {
        this.userName = userName;
        this.password = password;
        this.userType = "Visitor";
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
        System.out.println("Логин посетителя: " + userName);
    }
}
