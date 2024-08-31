package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.UserDao;
import cn.shivam.model.User;

//@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateUserServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");

        if (!"Merchant".equals(userType) && !"Customer".equals(userType)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid user type. Must be 'Merchant' or 'Customer'.");
            return;
        }

        UserDao userDao;
        try {
            userDao = new UserDao(DBCon.getConnection());
            User existingUser = userDao.getUserById(userId);
            if (existingUser == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            String password = existingUser.getPassword();

            User user = new User();
            user.setId(userId);
            user.setName(name);
            user.setEmail(email);
            user.setUserType(userType);
            user.setPassword(password);

            boolean updateResult = userDao.updateUser(user);

            if (updateResult) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
