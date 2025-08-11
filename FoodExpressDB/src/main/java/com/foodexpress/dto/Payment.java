package com.foodexpress.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int orderId;
    private String paymentMethod; // cash, card, upi
    private String paymentStatus; // pending, success, failed
    private BigDecimal amountPaid;
    private Timestamp transactionTime;

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }
}

