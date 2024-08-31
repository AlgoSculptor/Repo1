package cn.shivam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.shivam.model.Product;
import cn.shivam.model.Cart;

public class ProductDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }
    public ProductDao() {
    	
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();

        try {
            query = "SELECT * FROM products";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category")); 
                row.setPrice(rs.getDouble("price")); 
                row.setImage(rs.getString("image"));

                products.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList){
    	List<Cart> products = new ArrayList<Cart>();
    	
    	try {
    		
    		if(cartList.size()>0) {
    			
    			for(Cart item: cartList) {
    				query = "SELECT * FROM products WHERE id=?";
    				pst = this.con.prepareStatement(query);
    				pst.setInt(1, item.getId());
    				rs = pst.executeQuery();
    				while(rs.next()) {
    					Cart row = new Cart();
    					row.setId(rs.getInt("id"));
    					row.setName(rs.getString("name"));
    					row.setCategory(rs.getString("category"));
    					row.setPrice(rs.getDouble("price")*item.getQuantity()); 
                        row.setQuantity(item.getQuantity()); // Added line for quantity
    					products.add(row); 
    				}
    			}
    			
    		}
    		
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return products;
    }
    
    public Product getSingleProduct(int id) {
    	Product row = null;
    	
    	
    	try {
    		query = "SELECT * FROM products WHERE id=?";
    		pst = this.con.prepareStatement(query);
    		pst.setInt(1, id);
    		rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			row = new Product();
    			row.setId(rs.getInt("id"));
    			 row.setName(rs.getString("name"));
    			row.setCategory(rs.getString("category"));
    			row.setPrice(rs.getDouble("price"));
    			row.setImage(rs.getString("image"));
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return row;
    }
    
    public double getTotalCartPrice(ArrayList<Cart> cartList) {
    	double sum =0;
    	
    	try {
    		
    		if(cartList.size()>0) {
    			for(Cart item : cartList) {

    	    		query = "SELECT price FROM products WHERE id=?;";
    	    		pst = this.con.prepareStatement(query);
    	    		pst.setInt(1, item.getId());
    	    		rs = pst.executeQuery();
    	    		
    	    		while(rs.next()) {
    	    			sum +=rs.getDouble("price") * item.getQuantity();
    	    			
    	    		}
    			}
    		}
    		
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	return sum;
    }
    
    public List<Product> searchProducts(String name, String category) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? OR category LIKE ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "%" + name + "%");
            pst.setString(2, "%" + category + "%");
            
            // Debugging statements to print the SQL query and parameter values
            System.out.println("SQL Query: " + pst.toString());
            System.out.println("Name Parameter: %" + name + "%");
            System.out.println("Category Parameter: %" + category + "%");
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCategory(rs.getString("category"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImage(rs.getString("image"));
                    products.add(product);
                }
            }
        }
        return products;
    }


    public boolean addProduct(String name, String category, double price, int quantity) {
        boolean isSuccess = false;

        try {
            // Define the SQL query to insert a new product
            String sql = "INSERT INTO products (name, category, price, quantity) VALUES (?, ?, ?, ?)";

            // Create a PreparedStatement object
            pst = con.prepareStatement(sql);

            // Set the parameter values for the PreparedStatement
            pst.setString(1, name);
            pst.setString(2, category);
            pst.setDouble(3, price);
            pst.setInt(4, quantity);

            // Execute the query
            int rowsAffected = pst.executeUpdate();

            // Check if the product insertion was successful
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }
    
    
    public boolean updateProduct(Product product) {
        boolean result = false;
        try {
            String query = "UPDATE products SET name = ?, category = ?, price = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getId());
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean removeProduct(int id) {
        boolean result = false;
        try {
            query = "DELETE FROM products WHERE id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
