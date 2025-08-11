package com.foodexpress.dto;

import java.sql.Timestamp;

public class Delivery {
    private int deliveryId;
    private int orderId;
    private String deliveryPersonName;
    private String deliveryPersonPhone;
    private String status; // assigned, picked_up, on_the_way, delivered, failed
    private Timestamp assignedTime;
    private Timestamp deliveredTime;

    // Getters and Setters
    public int getDeliveryId() {
        return deliveryId;
    }
    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryPersonName() {
        return deliveryPersonName;
    }
    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }

    public String getDeliveryPersonPhone() {
        return deliveryPersonPhone;
    }
    public void setDeliveryPersonPhone(String deliveryPersonPhone) {
        this.deliveryPersonPhone = deliveryPersonPhone;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getAssignedTime() {
        return assignedTime;
    }
    public void setAssignedTime(Timestamp assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Timestamp getDeliveredTime() {
        return deliveredTime;
    }
    public void setDeliveredTime(Timestamp deliveredTime) {
        this.deliveredTime = deliveredTime;
    }
}

