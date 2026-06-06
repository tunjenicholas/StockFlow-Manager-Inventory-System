package com.inventory.dao;

import com.inventory.util.DBConnection;
import com.inventory.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // TOTAL SALES
    public double getTotalSales() {

        double total = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT NVL(SUM(total_price),0) AS total FROM sales");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getDouble("total");
                System.out.println("TOTAL SALES = " + total);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    // TODAY SALES
    public double getTodaySales() {

        double total = 0;

        String sql = "SELECT NVL(SUM(total_price),0) AS total " +
                     "FROM sales WHERE TRUNC(sale_date)=TRUNC(SYSDATE)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    // LOW STOCK COUNT
    public int getLowStockCount() {

        int count = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT COUNT(*) AS cnt FROM products WHERE quantity < 10");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("cnt");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    
    public List<Product> getLowStockProducts() {

        List<Product> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT product_id, name, quantity, price FROM products WHERE quantity < 10";

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
}