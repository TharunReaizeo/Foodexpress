package com.foodexpress.dao;

import java.sql.*;

import com.foodexpress.dto.Delivery;
import com.foodexpress.util.DBConnection;


public class DeliveryDAO {

    // 1. Assign Delivery
    public boolean assignDelivery(Delivery delivery) {
        String sql = "INSERT INTO delivery (order_id, delivery_person_name, delivery_person_phone, status) "
                   + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, delivery.getOrderId());
            ps.setString(2, delivery.getDeliveryPersonName());
            ps.setString(3, delivery.getDeliveryPersonPhone());
            ps.setString(4, delivery.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Update Delivery Status
    public boolean updateDeliveryStatus(int deliveryId, String status) {
        String sql = "UPDATE delivery SET status = ?, delivered_time = CURRENT_TIMESTAMP WHERE delivery_id = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, deliveryId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Get Delivery by Order ID
    public Delivery getDeliveryByOrderId(int orderId) {
        String sql = "SELECT * FROM delivery WHERE order_id = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Delivery d = new Delivery();
                d.setDeliveryId(rs.getInt("delivery_id"));
                d.setOrderId(rs.getInt("order_id"));
                d.setDeliveryPersonName(rs.getString("delivery_person_name"));
                d.setDeliveryPersonPhone(rs.getString("delivery_person_phone"));
                d.setStatus(rs.getString("status"));
                d.setAssignedTime(rs.getTimestamp("assigned_time"));
                d.setDeliveredTime(rs.getTimestamp("delivered_time"));
                return d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

