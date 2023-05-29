package models.request.user;

public class UserLoginRequestModel {
    public String email;
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLoginRequestModel() {
    }
}