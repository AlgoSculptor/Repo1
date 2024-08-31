package cn.shivam.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import cn.shivam.connection.DBCon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

//@WebServlet("/add-products2")
@MultipartConfig
public class AddProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AddProductsServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("AddProductsServlet invoked");

        // Retrieve product information from the form
        String productName = request.getParameter("productName");
        String productCategory = request.getParameter("productCategory");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        // Save the image file
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        String imagePath = saveImageFile(fileName, fileContent);

        // Insert product into the database
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBCon.getConnection();
            String sql = "INSERT INTO products (name, category, price, quantity, image) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, productName);
            pst.setString(2, productCategory);
            pst.setDouble(3, productPrice);
            pst.setInt(4, productQuantity);
            pst.setString(5, imagePath);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Product added successfully");
                response.sendRedirect("index.jsp"); // Redirect to the home page
                return;
            } else {
                logger.warning("Failed to add product");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.warning("Error adding product: " + e.getMessage());
        } finally {
            // Close PreparedStatement only
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        
        // If product addition failed, redirect back to addProduct.jsp with an error parameter
        response.sendRedirect("addProduct.jsp?error=true");
    }

    private String saveImageFile(String fileName, InputStream fileContent) throws IOException {
        Path uploads = Paths.get(getServletContext().getRealPath("/product-images"));
        Path file = Files.createTempFile(uploads, "", fileName);
        try (InputStream input = fileContent) {
            Files.copy(input, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        return file.getFileName().toString();
    }
}
