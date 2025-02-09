package com.orn.aispl;

public class Member {
    private String name, mobileNo, role, subscriptionAmt, loanAmt, depositAmt;

    public Member(String name, String mobileNo, String role, String subscriptionAmt, String loanAmt, String depositAmt) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.role = role;
        this.subscriptionAmt = subscriptionAmt;
        this.loanAmt = loanAmt;
        this.depositAmt = depositAmt;
    }

    // Getters
    public String getName() { return name; }
    public String getMobileNo() { return mobileNo; }
    public String getRole() { return role; }
    public String getSubscriptionAmt() { return subscriptionAmt; }
    public String getLoanAmt() { return loanAmt; }
    public String getDepositAmt() { return depositAmt; }
}

