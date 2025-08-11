package com.foodexpress.dao;



import java.sql.*;
import java.util.*;

import com.foodexpress.dto.Restaurant;
import com.foodexpress.util.DBConnection;

public class AdminDAO {

    // 1. Get all users
    public List<Integer> getAllUsers() {
        List<Integer> userList = new ArrayList<>();
        String sql = "SELECT user_id FROM user";

        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // 2. Delete a user by ID
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Get pending restaurants (is_verified = 0)
    public List<Restaurant> getPendingRestaurants() {
        List<Restaurant> pendingList = new ArrayList<>();
        String sql = "SELECT * FROM restaurant WHERE is_verified = 0";

        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setRestaurantName(rs.getString("restaurant_name"));
                r.setEmail(rs.getString("email"));
                r.setCity(rs.getString("city"));
                r.setStatus(rs.getString("status"));
                r.setVerified(false); // because it's pending
                pendingList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    // 4. Approve restaurant by setting is_verified = 1
    public boolean approveRestaurant(int restaurantId) {
        String sql = "UPDATE restaurant SET is_verified = 1 WHERE restaurant_id = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sql)) {
            ps.setInt(1, restaurantId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

