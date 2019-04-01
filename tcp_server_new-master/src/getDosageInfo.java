import java.sql.*;

public class getDosageInfo {
    // JDBC driver name and database URL
    /*
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/";
    // Database credentials
    static final String username = "root";
    static final String password = "118129Zszy!";
    */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9281675";
    //  Database credentials
    static final String username = "sql9281675";
    static final String password = "CrxlPUYT21";

    public static String getInfo(String ID) {
        String info = "";
        System.out.println("Connecting database...");
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected");
            System.out.println("Selecting");
            for (int i = 1; i <= 10; i++) {//如果存的不到10个，怎么处理？
               // info += "box";
                String index = Integer.toString(i);
               // info += index;
                try {
                    String query = "SELECT PillName" + index +
                            " FROM sql9281675.box" + ID + ";";
                    ResultSet result = stmt.executeQuery(query);
                    if(!result.getString("PillName" + index).equals("") && result.getString("PillName" + index) != null) {
                        info += "p";
                        info += Integer.toString(i - 1);
                    }
                    query = "SELECT time" + index + //get number of pills to take
                            ", dos" + index + //get time to take the pill
                            " FROM sql9281675.box" + ID + ";";
                    stmt = conn.createStatement();
                    result = stmt.executeQuery(query);
                    while (result.next()) {
                        String time = result.getString("time" + index);
                        //System.out.println("time: " + time);
                        if (time != null && !time.equals("")) {
                            info += "t";
                            info += time;
                        }
                        String dos = result.getString("dos" + index);
                        //System.out.println("dos: " + dos);
                        if (dos != null && !dos.equals("")) {
                            info += "n";
                            info += dos;
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
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
