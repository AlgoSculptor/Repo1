<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <title>Service Records</title>
</head>
<body>
  <h2 align="center"><font><strong>Records Of Services</strong></font></h2>
  <div class="container-fluid">
    <table class="table table-striped">
      <thead>
        <tr>
          <td><b>Id</b></td>
          <td><b>Name</b></td>
          <td><b>Licence no</b></td>
          <td><b>Adhar no</b></td>
          <td><b>City</b></td>
          <td><b>Location</b></td>
          <td><b>Service type</b></td>
          <td><b>Sub Service Type</b></td>
          <td><b>Instruction</b></td>
          <td><b>Date Time</b></td>
          <td><b>Vehicle Name</b></td>
          <td><b>Vehicle no</b></td>
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

          // Handle service record removal
          String serviceIdToRemove = request.getParameter("serviceId");
          if (serviceIdToRemove != null) {
            String deleteSql = "DELETE FROM service WHERE id=" + serviceIdToRemove;
            statement.executeUpdate(deleteSql);
            out.println("<div class='alert alert-success'>Service record removed successfully!</div>");
          }

          // Fetch and display service records
          String sql = "SELECT * FROM service";
          resultSet = statement.executeQuery(sql);
          while (resultSet.next()) {
        %>
        <tr>
          <td><%= resultSet.getString("id") %></td>
          <td><%= resultSet.getString("name") %></td>
          <td><%= resultSet.getString("licence_no") %></td>
          <td><%= resultSet.getString("adhar_no") %></td>
          <td><%= resultSet.getString("city") %></td>
          <td><%= resultSet.getString("location") %></td>
          <td><%= resultSet.getString("service_type") %></td>
          <td><%= resultSet.getString("sub_service_type") %></td>
          <td><%= resultSet.getString("instruction") %></td>
          <td><%= resultSet.getString("date_time") %></td>
          <td><%= resultSet.getString("vehicle_name") %></td>
          <td><%= resultSet.getString("vehicle_no") %></td>
          <td>
            <form method="post" action="">
              <input type="hidden" name="serviceId" value="<%= resultSet.getString("id") %>">
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
