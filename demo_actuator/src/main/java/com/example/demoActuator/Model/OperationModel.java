package com.example.demoActuator.Model;

import java.util.ArrayList;
import java.util.List;

public class OperationModel {

    private Long id;

    private String transactionId;

    private String accountNumber;
    private String accountBranchCode;
    private String accountKey;

    private String customerNumber;

    private Double amount;
    private String transactionDescription;

//    private List<CategoryResource> categoryResources = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

//    public List<CategoryResource> getCategoryResources() {
//        return categoryResources;
//    }
//
//    public void setCategoryResources(List<CategoryResource> categoryResources) {
//        this.categoryResources = categoryResources;
//    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccountBranchCode() {
        return accountBranchCode;
    }

    public void setAccountBranchCode(String accountBranchCode) {
        this.accountBranchCode = accountBranchCode;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
