package ru.stqa.jt.mantis.appmanager;

import ru.stqa.jt.mantis.model.UserData;
import ru.stqa.jt.mantis.model.Users;

import java.sql.*;


public class DbHelper {
  private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    this.app = app;
  }

  public Users mantisUsers() {

    Connection conn = null;
    Users users = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
      users = new Users();
      while (rs.next()) {
        users.add(new UserData().withId(rs.getInt("id"))
                .withUsername(rs.getString("username"))
                .withEmail("email"));
      }
      rs.close();
      st.close();
      conn.close();

      System.out.println(users);

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return users;
  }
}
