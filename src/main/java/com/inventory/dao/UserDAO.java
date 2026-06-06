package com.inventory.dao;

import com.inventory.util.DBConnection;
import com.inventory.util.PasswordUtil;
import com.inventory.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	public User validateUser(String username, String password) {

	    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {

	        ps.setString(1, username);
	        ps.setString(2, PasswordUtil.hashPassword(password));

	        try(ResultSet rs = ps.executeQuery()) {

	            if(rs.next()) {

	                User user = new User();

	                user.setUserId(rs.getInt("user_id"));
	                user.setUsername(rs.getString("username"));
	                user.setRole(rs.getString("role"));

	                return user;
	            }
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}
