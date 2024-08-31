
package cn.shivam.servlet;
// Import statements

//@WebServlet(name = "AdminManageUsers", urlPatterns = {"/admin-manage-users"})

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.UserDao;
import cn.shivam.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminManageUsers extends HttpServlet {
    private UserDao userDao;

    public void init() {
        try {
			userDao = new UserDao(DBCon.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in and is an admin
        User auth = (User) request.getSession().getAttribute("auth");
        
        // Retrieve all users
        List<User> users = userDao.getAllUsers();
        request.setAttribute("users", users);

        // Forward to admin manage users view
        request.getRequestDispatcher("admin_manage_users.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle post requests, such as adding or deleting a user
    }
}
