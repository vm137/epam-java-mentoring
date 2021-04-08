import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

  public static void main(String[] args) throws SQLException {
    Connection conn = Configuration.connect();
    Statement stmt = conn.createStatement();
    String sql = "SELECT * FROM students";
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
      String id  = rs.getString("student_id");
      String first = rs.getString("first_name");
      String last = rs.getString("last_name");
      String skill = rs.getString("primary_skill");

      System.out.print("ID: " + id);
      System.out.print(", First: " + first);
      System.out.print(", Last: " + last);
      System.out.println(", Skill: " + skill);
    }

    rs.close();
    stmt.close();
    conn.close();
  }
}
