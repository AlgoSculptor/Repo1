<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <title>User Records</title>
</head>
<body>
  <h2 align="center"><font><strong>Records Of Users</strong></font></h2>
  <div class="container-fluid">
    <table class="table table-striped">
      <thead>
        <tr>
          <td><b>Id</b></td>
          <td><b>Name</b></td>
          <td><b>E-mail</b></td>
          <td><b>MobileNo</b></td>
          <td><b>Action</b></td>
        </tr>
      </thead>
      <tbody>
        <%
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/highway", "root", "abcdef");
          statement = connection.createStatement();

          // Handle user removal
          String userIdToRemove = request.getParameter("userId");
          if (userIdToRemove != null) {
            String deleteSql = "DELETE FROM users WHERE id=" + userIdToRemove;
            statement.executeUpdate(deleteSql);
            out.println("<div class='alert alert-success'>User removed successfully!</div>");
          }

          // Fetch and display users
          String sql = "SELECT * FROM users";
          resultSet = statement.executeQuery(sql);
          while (resultSet.next()) {
        %>
        <tr>
          <td><%= resultSet.getString("id") %></td>
          <td><%= resultSet.getString("name") %></td>
          <td><%= resultSet.getString("email") %></td>
          <td><%= resultSet.getString("mobileno") %></td>
          <td>
            <form method="post" action="">
              <input type="hidden" name="userId" value="<%= resultSet.getString("id") %>">
              <button type="submit" class="btn btn-danger">Remove</button>
            </form>
          </td>
        </tr>
        <%
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { if (resultSet != null) resultSet.close(); } catch (Exception e) { }
          try { if (statement != null) statement.close(); } catch (Exception e) { }
          try { if (connection != null) connection.close(); } catch (Exception e) { }
        }
        %>
      </tbody>
    </table>
  </div>
</body>
</html>
