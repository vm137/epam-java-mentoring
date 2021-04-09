import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Configuration {

  Configuration() throws ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
  }

  static Connection connect() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/cdp";

    Properties props = new Properties();
    props.setProperty("user","postgres");
    props.setProperty("password","admin");
    props.setProperty("ssl","false");

    return DriverManager.getConnection(url, props);
  }
}


/*
 * https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
 */
