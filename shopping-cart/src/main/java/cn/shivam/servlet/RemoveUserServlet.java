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

//@WebServlet("/remove-user")
public class RemoveUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveUserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            UserDao userDao;
			try {
				userDao = new UserDao(DBCon.getConnection());
				userDao.deleteUser(Integer.parseInt(id));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        response.sendRedirect("admin_manage_users.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
