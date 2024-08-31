package cn.shivam.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String userType; // Adding userType attribute

    // Constructors
    public User() {
    }

    public User(int id, String name, String email, String password, String userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType != null ? userType : ""; // Return an empty string if userType is null
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // toString method
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", userType=" + userType + "]";
    }
}
