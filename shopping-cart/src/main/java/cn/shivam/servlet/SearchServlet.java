//URL = "/search"

package cn.shivam.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cn.shivam.connection.DBCon;
import cn.shivam.dao.ProductDao;
import cn.shivam.model.Product;

//@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        
        try {
            ProductDao productDao = new ProductDao(DBCon.getConnection());
            List<Product> searchResults = productDao.searchProducts(query, query);
            
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("search-results.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("An error occurred: " + ex.getMessage());
        }
    }

}
