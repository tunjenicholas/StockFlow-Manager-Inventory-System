# 📦 StockFlow Manager  
### Inventory & Sales Management System (Java + Oracle + JSP/Servlets)

---

## 🧠 Overview

**StockFlow Manager** is a Java-based web application designed to manage inventory, track product sales, and monitor stock levels for small to medium-sized businesses.

It supports **role-based access control (Admin & Clerk)** and provides real-time insights into inventory and sales performance.

---

## 🚀 Key Features

### 👨‍💼 Admin Features
- Add, update, and delete products
- View detailed sales reports (daily & total sales)
- Monitor low-stock products
- Full system control and access management

### 👨‍💻 Clerk Features
- View product inventory
- Record sales transactions
- View sales reports (read-only access)
- Restricted access to admin operations

---

## 🔐 Security Features
- Password hashing using **SHA-256**
- Session-based authentication
- Role-Based Access Control (RBAC)
- Servlet Filter-based route protection (AuthFilter)
- Secure login and logout system

---

## 📊 System Modules
- User Authentication Module
- Product Management Module
- Sales Processing Module
- Reporting & Analytics Module
- Inventory Monitoring Module

---

## 🏗️ System Architecture
- Frontend → JSP, HTML, CSS, Bootstrap
- Backend → Java Servlets (Jakarta EE)
- Database → Oracle SQL
- Pattern → MVC Architecture

---

## 🧩 Database Schema

- USERS  
- PRODUCTS  
- SALES  

---

## ⚙️ Technologies Used

- Java 17+
- Jakarta Servlet API
- JSP (Java Server Pages)
- Oracle Database
- JDBC
- Bootstrap 5
- HTML, CSS, JavaScript

---

## 📸 Screenshots

> Add screenshots here after deployment

- Login Page  
- Admin Dashboard  
- Clerk Dashboard  
- Product Management Page  
- Sales Report Dashboard  

---

## 📂 Project Structure
com.inventory
├── controller (Servlets)
├── dao (Database Logic)
├── model (Entities)
├── util (DB & Security Helpers)
├── filter (Authentication Filters)


---

## 🔄 Workflow

1. User logs in
2. System validates credentials
3. Role-based dashboard is loaded
4. Admin manages products and reports
5. Clerk records sales
6. Reports update dynamically

---

## ⚠️ Business Rules

- Products cannot be deleted if referenced in sales records
- Clerk has restricted access to admin functionalities
- Stock is automatically reduced after a sale
- All transactions are handled safely using database consistency rules

---

## 📈 Future Improvements

- AJAX-based UI (no page reloads)
- REST API integration
- Mobile application version
- Dashboard analytics with charts
- Soft delete system for products
- Audit logging (track user activity)

---

## 👨‍💻 Author

**Nicholas Tunje**  
Java Full Stack Developer (Student Project)

- Email: nm.tunje@gmail.com  
- GitHub: *(add your link)*  
- LinkedIn: *(add your link)*  

---

## 📜 License

This project is for educational and portfolio purposes. It can be extended for commercial use.

---
