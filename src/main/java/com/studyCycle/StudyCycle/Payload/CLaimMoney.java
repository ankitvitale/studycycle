package com.studyCycle.StudyCycle.Payload;

public class CLaimMoney {
    public Long userid;
    public String username;
    public String UpiId;
    public Double claimedMoney;
    public Double WalletAmount;

    public CLaimMoney(Long userid, String username, String upiId, Double claimedMoney, Double walletAmount) {
        this.userid = userid;
        this.username = username;
        UpiId = upiId;
        this.claimedMoney = claimedMoney;
        WalletAmount = walletAmount;
    }
}
