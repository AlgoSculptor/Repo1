package cn.shivam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.shivam.model.User;

public class UserDao {
    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public boolean registerUser(User user) {
        // Implement user registration logic here
        // Return true if registration is successful, false otherwise
        return false;
    }

    public User userLogin(String email, String password) {
        User user = null;
        try {
            // Prepare SQL query to retrieve user information based on email and password
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            
            // Execute the query
            ResultSet rs = pst.executeQuery();
            
            // If the result set has a row, the user exists with the provided credentials
            if (rs.next()) {
                // Create a new User object and populate it with the retrieved data
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                // Set other user attributes as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public String getUserType(String email) {
        String userType = null;
        try {
            // Prepare SQL query to retrieve user type based on email
            String query = "SELECT userType FROM users WHERE email = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            
            // Execute the query
            ResultSet rs = pst.executeQuery();
            
            // If the result set has a row, retrieve the user type
            if (rs.next()) {
                userType = rs.getString("userType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userType;
    }
    
    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("userType"));
                // Set other user attributes as needed
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    
    public User getUserById(int id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // Get the current password
                user.setUserType(rs.getString("userType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
    
    public boolean updateUser(User user) {
        boolean result = false;
        try {
            String query = "UPDATE users SET name = ?, email = ?, password = ?, userType = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getUserType());
            pst.setInt(5, user.getId());
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteUser(int id) {
        boolean result = false;
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
