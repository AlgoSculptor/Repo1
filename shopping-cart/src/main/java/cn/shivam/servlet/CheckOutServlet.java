package cn.shivam.servlet;


//URL = "/cart-check-out"

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;
import cn.shivam.model.Cart;
import cn.shivam.model.Order;
import cn.shivam.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()){
			//out.println("Check out servlet");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			
			//retrieve all cart products
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			//User-Authentication
			User auth = (User)request.getSession().getAttribute("auth");
			
			//Check auth and cart list
			
			if(cart_list != null && auth != null){
				
				for(Cart c : cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setuId(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDao oDao = new OrderDao(DBCon.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
			}
		else{
				if(auth==null)response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

// 9.33
