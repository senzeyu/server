import java.sql.*;

public class getPillname {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";
    //  Database credentials
    static final String username = "root";
    static final String password = "118129Zszy!";

    public static String get(String serial_ID, String seg_ID) {
        String info = "";
        System.out.println("Connecting database...");
        Connection conn = null;
        Statement stmt = null;
        try {//connecting to database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected");
            System.out.println("Selecting");
            try {//selecting specific info
                String query = "select PillNmae" + seg_ID + "from SafeTPill.box" + serial_ID + ";";
                stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query);
                while(result.next()){
                    String tmpinfo = result.getString("Email");
                    if(tmpinfo != null){
                        info = tmpinfo;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
            System.out.println(info);
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
