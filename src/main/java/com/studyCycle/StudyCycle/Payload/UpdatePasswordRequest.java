package com.studyCycle.StudyCycle.Payload;

public class UpdatePasswordRequest {
    private String email;
    private String newPassword;
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmailOrPhone(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
