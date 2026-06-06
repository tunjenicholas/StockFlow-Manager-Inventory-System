package com.inventory.dao;

import com.inventory.util.DBConnection;
import com.inventory.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // ADD PRODUCT
	public boolean addProduct(Product p) {

	    String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, p.getName());
	        ps.setInt(2, p.getQuantity());
	        ps.setDouble(3, p.getPrice());

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM products";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE PRODUCT
    public boolean updateProduct(Product p) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE products SET name=?, quantity=?, price=? WHERE product_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getQuantity());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getProductId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // EDIT PRODUCT
    public Product getProductById(int id) {

        Product p = null;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM products WHERE product_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    // DELETE PRODUCT
    public String deleteProduct(int id) {

        String sql = "DELETE FROM products WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return "SUCCESS";
            }

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            return "CHILD_EXISTS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "NOT_FOUND";
    }
    
    // STOCK REDUCTION
    public boolean reduceStock(int productId, int qty) {

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE products SET quantity = quantity - ? WHERE product_id = ? AND quantity >= ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setInt(2, productId);
            ps.setInt(3, qty);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}