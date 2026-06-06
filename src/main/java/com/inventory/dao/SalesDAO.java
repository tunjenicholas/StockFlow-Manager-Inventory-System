package com.inventory.dao;

import com.inventory.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesDAO {

	public boolean recordSale(int productId, int quantity) {

	    Connection conn = null;

	    try {
	        conn = DBConnection.getConnection();
	        conn.setAutoCommit(false);

	        String checkSql = "SELECT quantity, price FROM products WHERE product_id=?";
	        PreparedStatement checkPs = conn.prepareStatement(checkSql);
	        checkPs.setInt(1, productId);

	        ResultSet rs = checkPs.executeQuery();

	        if (!rs.next()) return false;

	        int stock = rs.getInt("quantity");
	        double price = rs.getDouble("price");

	        if (stock < quantity) return false;

	        double total = price * quantity;

	        PreparedStatement insertPs = conn.prepareStatement(
	            "INSERT INTO sales (product_id, quantity, total_price) VALUES (?, ?, ?)"
	        );
	        insertPs.setInt(1, productId);
	        insertPs.setInt(2, quantity);
	        insertPs.setDouble(3, total);
	        insertPs.executeUpdate();

	        PreparedStatement updatePs = conn.prepareStatement(
	            "UPDATE products SET quantity = quantity - ? WHERE product_id=?"
	        );
	        updatePs.setInt(1, quantity);
	        updatePs.setInt(2, productId);
	        updatePs.executeUpdate();

	        conn.commit();
	        return true;

	    } catch (Exception e) {

	        try {
	            if (conn != null) conn.rollback();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        e.printStackTrace();
	    }

	    return false;
	}
}