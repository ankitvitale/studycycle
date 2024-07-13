package com.studyCycle.StudyCycle.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String fullName; // Changed from userFirstName
    private String phoneNumber; // Changed from userLastName
    private String password; // Changed from userPassword


    private String usertype;
    private String verificationCode;
    private String resetCode;
    private boolean verified;

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    private String upi_id;


    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    private Double wallet;

    public LocalDateTime getClaimingTime() {
        return claimingTime;
    }

    public void setClaimingTime(LocalDateTime claimingTime) {
        this.claimingTime = claimingTime;
    }

    private LocalDateTime claimingTime;

    public Double getClaimedMoney() {
        return claimedMoney;
    }

    public void setClaimedMoney(Double claimedMoney) {
        this.claimedMoney = claimedMoney;
    }

    private Double claimedMoney;

    public Double getClaims() {
        return claims;
    }

    public void setClaims(Double claims) {
        this.claims = claims;
    }

    private Double claims;
    public String getClaimstatus() {
        return claimstatus;
    }

    public void setClaimstatus(String claimstatus) {
        this.claimstatus = claimstatus;
    }

    private String claimstatus;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @OneToOne
    private Address defultaddresse;

    public User(String string, Object object, String fullName2, String password2) {
        // TODO Auto-generated constructor stub
    }

    public User() {
        // TODO Auto-generated constructor stub
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsertype() {
        return usertype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getDefultaddresse() {
        return defultaddresse;
    }

    public void setDefultaddresse(Address defultaddresse) {
        this.defultaddresse = defultaddresse;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


}
