package com.foodexpress.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.foodexpress.dto.Restaurant;
import com.foodexpress.util.DBConnection;

public class ResturantDAO {
	
	
  //Add or Register restaurant 
	public boolean addRestaurant(Restaurant r) {
		
		String iqry = "INSERT INTO restaurant(restaurant_name, email, password, phone, owner_name," 
				+ " address, city, pincode, cuisine_type, avg_cost_for_two, opening_time, closing_time,"
				+ " status, logo, rating, is_verified) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		try(PreparedStatement ps = DBConnection.giveConnection().prepareStatement(iqry)) {
			
			ps.setString(1, r.getRestaurantName());
			ps.setString(2, r.getEmail());
			ps.setString(3, r.getPassword());
			ps.setString(4, r.getPhone());
			ps.setString(5, r.getOwnerName());
			ps.setString(6, r.getAddress());
			ps.setString(7, r.getCity());
			ps.setString(8, r.getPincode());
			ps.setString(9, r.getCuisineType());
			ps.setBigDecimal(10, r.getAvgCostForTwo());
			ps.setTime(11, r.getOpeningTime());
			ps.setTime(12, r.getClosingTime());
			ps.setString(13, r.getStatus());
			ps.setString(14, r.getLogo());
			ps.setBigDecimal(15, r.getRating());
			ps.setBoolean(16, r.isVerified());
			
			return ps.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public Restaurant getRestaurantById(int id) {
		
		String sqry="SELECT * FROM  restaurant WHERE restaurant_id = ?";
		try(PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sqry)) {
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                return extractRestaurantFromResultSet(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateRestaurant(Restaurant r) {
	    String uqry = "UPDATE restaurant SET restaurant_name=?, email=?, password=?, phone=?, owner_name=?,"
	               + " address=?, city=?, pincode=?, cuisine_type=?, avg_cost_for_two=?, opening_time=?,"
	               + " closing_time=?, status=?, logo=?, rating=?, is_verified=? WHERE restaurant_id=?";

	    try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(uqry)) {
	        ps.setString(1, r.getRestaurantName());
	        ps.setString(2, r.getEmail());
	        ps.setString(3, r.getPassword());
	        ps.setString(4, r.getPhone());
	        ps.setString(5, r.getOwnerName());
	        ps.setString(6, r.getAddress());
	        ps.setString(7, r.getCity());
	        ps.setString(8, r.getPincode());
	        ps.setString(9, r.getCuisineType());
	        ps.setBigDecimal(10, r.getAvgCostForTwo());
	        ps.setTime(11, r.getOpeningTime());
	        ps.setTime(12, r.getClosingTime());
	        ps.setString(13, r.getStatus());
	        ps.setString(14, r.getLogo());
	        ps.setBigDecimal(15, r.getRating());
	        ps.setBoolean(16, r.isVerified());
	        ps.setInt(17, r.getRestaurantId());

	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}



	private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
		 Restaurant r = new Restaurant();

		    r.setRestaurantId(rs.getInt("restaurant_id"));
		    r.setRestaurantName(rs.getString("restaurant_name"));
		    r.setEmail(rs.getString("email"));
		    r.setPassword(rs.getString("password"));
		    r.setPhone(rs.getString("phone"));
		    r.setOwnerName(rs.getString("owner_name"));
		    r.setAddress(rs.getString("address"));
		    r.setCity(rs.getString("city"));
		    r.setPincode(rs.getString("pincode"));
		    r.setCuisineType(rs.getString("cuisine_type"));
		    r.setAvgCostForTwo(rs.getBigDecimal("avg_cost_for_two"));
		    r.setOpeningTime(rs.getTime("opening_time"));
		    r.setClosingTime(rs.getTime("closing_time"));
		    r.setStatus(rs.getString("status"));
		    r.setLogo(rs.getString("logo"));
		    r.setRating(rs.getBigDecimal("rating"));
		    r.setVerified(rs.getBoolean("is_verified"));
		    r.setCreatedAt(rs.getTimestamp("created_at"));
		    r.setUpdatedAt(rs.getTimestamp("updated_at"));

		    return r;
	}

}
