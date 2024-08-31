package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import cn.shivam.dao.ProductDao;
import cn.shivam.model.Product;

//@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));

        // Update product data in the database
        boolean success = updateProductInDatabase(id, name, category, price);

        // Send response back to the client
        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // Method to update product data in the database
    private boolean updateProductInDatabase(int id, String name, String category, double price) {
        // Create an instance of ProductDao
        ProductDao productDao = new ProductDao();
        // Create a Product object with the updated data
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        // Call the updateProduct method of ProductDao
        return productDao.updateProduct(product);
    }
}
