package cn.shivam.servlet;

import cn.shivam.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

//@WebServlet("/add-to-cart")
public class AddToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = 1; // Default quantity to add

            // Get the current session
            HttpSession session = request.getSession();

            // Retrieve the existing cart list from the session
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            // If cart list doesn't exist, create a new one
            if (cartList == null) {
                cartList = new ArrayList<>();
            }

            // Check if the product is already in the cart
            boolean existsInCart = false;
            for (Cart item : cartList) {
                if (item.getId() == productId) {
                    existsInCart = true;
                    break;
                }
            }

            // If the product is not already in the cart, add it
            if (!existsInCart) {
                Cart cartItem = new Cart();
                cartItem.setId(productId);
                cartItem.setQuantity(quantity);
                cartList.add(cartItem);

                // Update the session attribute
                session.setAttribute("cart-list", cartList);

                // Redirect back to the referring page (either index.jsp or cart.jsp)
                response.sendRedirect(request.getHeader("referer"));
            } else {
                // Product already exists in the cart, show a message
                response.getWriter().println("<h3 style='color: crimson; text-align: center;'>Item already exists in the cart.</h3>");
                response.getWriter().println("<p style='text-align: center;'><a href='cart.jsp'>Go to Cart</a></p>");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: Invalid product ID</h3>");
        }
    }
}
