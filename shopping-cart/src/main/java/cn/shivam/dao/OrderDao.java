package cn.shivam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.shivam.model.Order;
import cn.shivam.model.Product;

public class OrderDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDao(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            String query = "INSERT INTO orders (p_id, u_id, o_date, o_quantity, o_address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getuId());
            pst.setString(3, model.getDate());
            pst.setInt(4, model.getQuantity());
            pst.setString(5, model.getDeliveryAddress()); // Set delivery address

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Order> fetchOrders(int id) {
        List<Order> list = new ArrayList<>();

        try {
            query = "SELECT * FROM orders WHERE u_id=? ORDER BY o_id DESC";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");

                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> userOrders(int id) {
        return fetchOrders(id);
    }

    public List<Order> userOrders() {
        return fetchOrders(0); // Call the existing method with a default value for id
    }
    
    

    public void cancelOrder(int id) {
        try {
            query = "DELETE FROM orders WHERE o_id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            query = "SELECT * FROM orders ORDER BY o_id DESC";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");

                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
                order.setQuantity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                order.setuId(rs.getInt("u_id"));
                order.setDeliveryAddress(rs.getString("o_address")); // Add this line to fetch address
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    
}
