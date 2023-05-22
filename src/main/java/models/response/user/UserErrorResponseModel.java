package models.response.user;

public class UserErrorResponseModel {
    public boolean success;
    public String message;

    public UserErrorResponseModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserErrorResponseModel() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}