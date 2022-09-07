import java.io.Console;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class JdbcCheck {

    public static void main(String args[]) {

        if (args.length != 3) {
            System.out.println("USAGE: java [-cp driver.jar" + File.pathSeparator +
                               ".] JdbcCheck driver ConnectString query");
            System.out.println("\nWhere\n");
            System.out.println("  driver class to use. Some examples:\n");
            System.out.println("    * oracle.jdbc.driver.OracleDriver");
            System.out.println("    * org.postgresql.Driver");
            System.out.println("    * com.mysql.cj.jdbc.Driver");
            System.out.println("    * com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("  ConnectString is the JDBC Connect String. Some examples:\n");
            System.out.println("    * jdbc:oracle:thin:@//server:1521/sid (Oracle DB)");
            System.out.println("    * jdbc:postgresql://server:5432/database (PostgreSQL)");
            System.out.println("  Query is the command to send to the database. Some examples:\n");
            System.out.println("    * select * from v$version");
            System.out.println("    * select version()");

            System.out.println("\n  driver.jar is JDBC driver for the database specified in ConnectString");

            return;
        }

        String driver = args[0];
        String url = args[1];
        String query = args[2];
        Connection con = null;

        try {
            System.out.println("Loading " + driver);
            Class.forName(driver).getConstructor().newInstance();

        } catch (Exception e) {
            System.err.println("Failed to load JDBC driver: " + e);
            return;
        }

        try {
            Console console = System.console();

            System.out.println("Connecting to " + url);
            String user = console.readLine("User? ");
            char[] pass = console.readPassword("Password? ");
            System.out.println();

            con = DriverManager.getConnection(url, user, new String(pass));
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery(query);

            while (result.next()) System.out.println(result.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
