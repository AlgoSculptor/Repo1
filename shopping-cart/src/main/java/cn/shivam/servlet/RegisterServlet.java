package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import cn.shivam.connection.DBCon;
import cn.shivam.dao.UserDao;
import cn.shivam.model.User;

//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        
        // Create a User object
        User user = new User(name, email, password, userType);
        
        // Insert the user into the database using UserDao
        UserDao userDao = null;
        try {
            userDao = new UserDao(DBCon.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean registrationSuccess = userDao.registerUser(user);
        
        if (registrationSuccess) {
            // Registration successful, redirect to login page
            response.sendRedirect("login.jsp");
        } else {
            // Registration failed, redirect back to register page with an error message
            response.sendRedirect("register.jsp?error=1");
        }
    }
}