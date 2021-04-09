import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Application {

  public static void main(String[] args) throws SQLException {
    Connection conn = Configuration.connect();

//    a. 100K of users
    Statement stmt = conn.createStatement();
    for (int i = 0; i < 100_000; i++) {
      String first = randomString();
      String last = randomString();
      String sql = "INSERT INTO students "
          + "(code, first, last) values "
          + "(" + (220 + i) + ", '" + first + "', '" + last + "');";
      stmt.executeUpdate(sql);
    }
    stmt.close();

//    b. 1K of subjects
//    for (int i = 0; i < 1000; i++) {
//
//    }

//    c. 1 million of marks
//    for (int i = 0; i < 1_000_000; i++) {
//
//    }
    conn.close();
  }

  public static String randomString() {
    int leftLimit = 97; // 'a'
    int rightLimit = 122; // 'z'
    int min = 5;
    int max = 12;

    Random random = new Random();
    int targetStringLength = random.nextInt(max - min) + min;
    return random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
