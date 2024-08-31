
//URL = "/navbar

package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import cn.shivam.model.User;

public class NavbarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Check if user is authenticated
            User auth = (User) request.getSession().getAttribute("auth");
            if (auth != null) {
                request.setAttribute("auth", auth);
            }
            // Forward the request to the navbar.jsp
            request.getRequestDispatcher("/navbar.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions
            response.getWriter().println("Error occurred while processing the request: " + e.getMessage());
        } 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        doGet(request, response);
    }
}
