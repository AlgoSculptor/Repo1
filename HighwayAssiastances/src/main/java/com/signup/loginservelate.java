package com.signup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class loginservelate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (email == null || email.equals("")) {
            request.setAttribute("status", "invalidemail");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (password == null || password.equals("")) {
            request.setAttribute("status", "invalidpassword");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try (Connection con = DbCon.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                session.setAttribute("name", rs.getString("name"));
                session.setAttribute("mobileNo", rs.getString("mobileno"));
                session.setAttribute("id", rs.getString("id"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("status", "database_error");
            dispatcher = request.getRequestDispatcher("error.jsp");
            e.printStackTrace();
            dispatcher.forward(request, response);
        }
    }
}
