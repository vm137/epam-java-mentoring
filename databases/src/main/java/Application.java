import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Application {

  public static void main(String[] args) throws SQLException {
    Connection conn = Configuration.connect();
    Statement stmt = conn.createStatement();
    String sql;
    ResultSet rs;

//    a. 100K of users
    String first;
    String last;
    for (int i = 0; i < 100_000; i++) {
      first = randomString();
      last = randomString();
      sql = "INSERT INTO students "
          + "(code, first, last) values "
          + "(" + (220 + i) + ", '" + first + "', '" + last + "');";
      stmt.executeUpdate(sql);
    }

//    b. 1K of subjects
    String title;
    for (int i = 0; i < 1_000; i++) {
      title = randomString();
      sql = "INSERT INTO subjects "
          + "(code, title) values "
          + "(" + (500 + i) + ", '" + title + "');";
      stmt.executeUpdate(sql);
    }

//    c. 20K of marks
    Random random = new Random();
    int student_code;
    int subject_code;
    for (int i = 0; i < 20_000; i++) {
      // student_code
      sql = "SELECT code FROM students ORDER BY RANDOM() LIMIT 1";
      rs = stmt.executeQuery(sql);
      rs.next();
      student_code = rs.getInt("code");

      // subject_code
      sql = "SELECT code FROM subjects ORDER BY RANDOM() LIMIT 1";
      rs = stmt.executeQuery(sql);
      rs.next();
      subject_code = rs.getInt("code");

      // mark
      int mark = random.nextInt(5) + 1;
      sql = "INSERT INTO exam_results "
          + "(student_code, subject_code, mark) values "
          + "(" + student_code + ", " + subject_code + ", " + mark + ");";
      stmt.executeUpdate(sql);
    }
    stmt.close();
    conn.close();
  }

  public static String randomString() {
    int leftLimit = 97; // 'a'
    int rightLimit = 122; // 'z'
    int min = 5; // min symbols
    int max = 12; // max symbols

    Random random = new Random();
    int targetStringLength = random.nextInt(max - min) + min;
    return random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
