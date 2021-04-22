import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class AddPhone {

  public static void main(String[] args) throws SQLException {
    Connection conn = Configuration.connect();
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
    String sql;
    ResultSet rs;

    sql = "SELECT * from students;";
    rs = stmt.executeQuery(sql);
    Random random = new Random();
    int phone;
    while (rs.next()) {
      int id = rs.getInt("id");
      phone = random.nextInt(1_000_000_000) + 1_000_000_000;
//    analog of "UPDATE students SET phone="+ phone +" WHERE id="+ id +";";
      rs.updateInt("phone", phone);
      rs.updateRow();
    }
    stmt.close();
    conn.close();
  }
}
