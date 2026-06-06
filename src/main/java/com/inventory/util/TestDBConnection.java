package com.inventory.util;

import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Connection is working!");
        } else {
            System.out.println("Connection failed!");
        }
        
		System.out.println(com.inventory.util.PasswordUtil.hashPassword("admin123"));
        System.out.println(com.inventory.util.PasswordUtil.hashPassword("clerk123"));
        
    }
}
