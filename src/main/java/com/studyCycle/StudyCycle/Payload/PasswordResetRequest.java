package com.studyCycle.StudyCycle.Payload;

public class PasswordResetRequest {
    private String emailOrPhone;

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }
}
