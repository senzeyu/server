import java.sql.*;
public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";
    //  Database credentials
    static final String username = "root";
    static final String password = "118129Zszy!";
    public static String selectfromDB(String ID, char opcode) {
        String info = null;
        System.out.println("Connecting database...");
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected");
            System.out.println("Selecting");
            if(opcode=='t' || opcode == 'f'){
                try {
                    String query = "SELECT Email  FROM login.login where UserID = " + ID + ";";
                    stmt = conn.createStatement();
                    ResultSet result = stmt.executeQuery(query);
                    info=result.getString("Email");
                    //while (result.next()
                    System.out.println("selected");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
            else {
                for(int i = 0; i <=9; i++){
                    String index = Integer.toString(i);
                    try {
                        String query = "SELECT PillName" + index + //get pill names
                                               ", time" + index + //get number of pills to take
                                               ", dos" + index + //get time to take the pill
                                               "FROM SafeTpill.box" + ID + ";";
                        stmt = conn.createStatement();
                        ResultSet result = stmt.executeQuery(query);
                        info=result.getString("Email");
                        while (result.next()){

                        }
                        System.out.println("selected");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (stmt != null) {
                            stmt.close();
                        }
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Done!");
        return info;
    }
}