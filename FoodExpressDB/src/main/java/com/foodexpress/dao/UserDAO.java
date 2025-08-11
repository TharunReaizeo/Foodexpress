package com.foodexpress.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.foodexpress.dto.User;
import com.foodexpress.util.DBConnection;

public class UserDAO {
	
	
 //To Insert user into the database	
	public boolean registerUser(User u) {
		
		String iqry = "INSERT INTO user(uName,uEmail,password,uPhone,gender,profile_image,address,city,pincode,user_role) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		  try(PreparedStatement ps = DBConnection.giveConnection().prepareStatement(iqry)) {
			  
			    ps.setString(1, u.getuName());
	            ps.setString(2, u.getuEmail());
	            ps.setString(3, u.getPassword());
	            ps.setString(4, u.getuPhone());
	            ps.setString(5, u.getGender());
	            ps.setString(6, u.getProfileImage());
	            ps.setString(7, u.getAddress());
	            ps.setString(8, u.getCity());
	            ps.setString(9, u.getPincode());
	            ps.setString(10, u.getUserRole());
	            
	            return ps.executeUpdate() > 0;
			  
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	
  //User Login
	public User validateUser(String uEmail , String password) {
		
		String sqry="SELECT * FROM user WHERE uEmail = ? and password = ?";
		
		try(PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sqry)) {
			ps.setString(1, uEmail);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				User user = extractUserFromResultSet(rs);
                return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	
	
 //Get user by ID
    public User getUserById(int userId) {
        String sqry = "SELECT * FROM user WHERE uId = ?";
        try(PreparedStatement ps = DBConnection.giveConnection().prepareStatement(sqry)) {
        	
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next())  
            	return extractUserFromResultSet(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
 //Update userInfo
    public boolean updateUser(User user) {
        String uqry = "UPDATE user SET uName = ?, uEmail = ?, uPhone = ?, gender = ?, profile_image = ?, address = ?, city = ?, pincode = ?, status = ?, is_verified = ? WHERE uId = ?";
        try (PreparedStatement ps = DBConnection.giveConnection().prepareStatement(uqry)) {

            ps.setString(1, user.getuName());
            ps.setString(2, user.getuEmail());
            ps.setString(3, user.getuPhone());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getProfileImage());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getPincode());
            ps.setString(9, user.getStatus());
            ps.setBoolean(10, user.getIsVerified());
            ps.setInt(11, user.getuId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
// Helper method to extract user data from ResultSet
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        
        user.setuId(rs.getInt("uId"));
        user.setuName(rs.getString("uName"));
        user.setuEmail(rs.getString("uEmail"));
        user.setPassword(rs.getString("password"));
        user.setuPhone(rs.getString("uPhone"));
        user.setGender(rs.getString("gender"));
        user.setProfileImage(rs.getString("profile_image"));
        user.setAddress(rs.getString("address"));
        user.setCity(rs.getString("city"));
        user.setPincode(rs.getString("pincode"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        user.setIsVerified(rs.getBoolean("is_verified"));
        user.setUserRole(rs.getString("user_role"));
        user.setStatus(rs.getString("status"));
        return user;
    }


}
