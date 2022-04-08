import java.sql.*;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/tris";
    static String username = "root";
    static String password = "";

    static Connection connection = null;
    static Statement dbst;
    static ResultSet result;

    public void init() {
        System.out.println("Provo a connettermi al Database...\n");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Non riesco ad utilizzare il Driver. \nMotivo: " + e + "\n");
        }
    
        try {
            connection = DriverManager.getConnection(url, username, password) ;
                             
            if (connection != null) {
                System.out.println("Database connesso!\n");
            }
        } catch (Exception e) {
            System.out.println("Non riesco a connettermi al Database. \nMotivo: " + e + "\n");
        }
    
        try {
            dbst = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Non riesco ad eseguire uno statement. \nMotivo: " + e + "\n");
        }
    }

    public void printDB() {
        try {
            result = dbst.executeQuery("SELECT * FROM ESPERIENZA");

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Non riesco ad eseguire una query. \nMotivo: " + e + "\n");
        }
    }

    public void addMatch(String moves, char finalOutcome) {
        String playerMoves = moves;
        char outcome = finalOutcome;

        try {
            dbst.executeUpdate("INSERT INTO esperienza (mosse, esito) VALUES ('" + playerMoves + "', '" + outcome + "')");
        } catch (Exception e) {
            System.out.println("Non riesco ad aggiungere la partita. \nMotivo: " + e + "\n");
        }
    }
}