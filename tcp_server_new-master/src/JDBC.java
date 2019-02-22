/*import java.sql.*;

public class JDBC {
    static final String driver = "";
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String user = "root";
    static final String pass = "118129Zszy!";
    public static void connect_db(){
        Connection conn = null;
        //Properties conn_prop = new Properties();
        conn = DriverManager.getConnection(url,user,pass);
        conn = DriverManager.getConnection(url,user,pass);
    }
}
*/
//STEP 1. Import required packages
import java.sql.*;
public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";

    //  Database credentials
    static final String username = "root";
    static final String password = "118129Zszy!";

    public static void selectfromDB(String ID) {
        System.out.println("Connecting database...");
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected");
            System.out.println("Selecting");
            String query = "SELECT *  FROM login.login where UserID = "+ID+";";
            try{
               stmt = conn.createStatement();
               ResultSet result=stmt.executeQuery(query);
               while(result.next()){
                   String last = result.getString("LastName");
                   String first = result.getString("FirstName");
                   String email = result.getString("Email");
                   System.out.println(last);
                   System.out.println(first);
                   System.out.println(email);
               }
               System.out.println("selected");
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(conn !=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
}