package com.foodexpress.dao;

import java.sql.*;

import com.foodexpress.dto.Payment;
import com.foodexpress.util.DBConnection;

public class PaymentDAO {

    // 1. Make Payment
    public boolean makePayment(Payment payment) {
        String sql = "INSERT INTO payment (order_id, payment_method, payment_status, amount_paid) "
                   + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setString(3, payment.getPaymentStatus());
            ps.setBigDecimal(4, payment.getAmountPaid());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Get Payment by Order ID
    public Payment getPaymentByOrderId(int orderId) {
        String sql = "SELECT * FROM payment WHERE order_id = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Payment p = new Payment();
                p.setPaymentId(rs.getInt("payment_id"));
                p.setOrderId(rs.getInt("order_id"));
                p.setPaymentMethod(rs.getString("payment_method"));
                p.setPaymentStatus(rs.getString("payment_status"));
                p.setAmountPaid(rs.getBigDecimal("amount_paid"));
                p.setTransactionTime(rs.getTimestamp("transaction_time"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

