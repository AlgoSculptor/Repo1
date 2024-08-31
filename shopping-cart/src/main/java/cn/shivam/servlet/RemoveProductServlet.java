package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.ProductDao;

//@WebServlet("/remove-product")
public class RemoveProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id != null) {
            
			try {
				ProductDao productDao;
				productDao = new ProductDao(DBCon.getConnection());
				productDao.removeProduct(Integer.parseInt(id));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        response.sendRedirect("admin-manage-products");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
