// AdminManageProducts.java
package cn.shivam.servlet;
// Import statements

//@WebServlet(name = "AdminManageProducts", urlPatterns = {"/admin-manage-products"})

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;
import cn.shivam.dao.ProductDao;
import cn.shivam.dao.UserDao;
import cn.shivam.model.Order;
import cn.shivam.model.Product;
import cn.shivam.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AdminManageProducts extends HttpServlet {
    private ProductDao productDao;

    public void init() {
        try {
			productDao = new ProductDao(DBCon.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in and is an admin
        User auth = (User) request.getSession().getAttribute("auth");
      
        // Retrieve all products
        List<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);

        // Forward to admin manage products view
        request.getRequestDispatcher("admin_manage_products.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle post requests, such as adding, editing, or deleting a product
    }
}