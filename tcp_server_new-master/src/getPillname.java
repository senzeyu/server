import java.sql.*;

public class getPillname {
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


    public static String get(String serial_ID, String seg_ID) {
        String info = "";  String final_info="";
        System.out.println("Connecting database...");
        Connection conn = null;
        Statement stmt = null;
        try {//connecting to database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successfully connected");
            System.out.println("Selecting");
            try {//selecting specific info
                int i=0;//cursor
                while(i<seg_ID.length()){
                    if(seg_ID.charAt(i)!='n'){
                        String query = "select PillName" + (i+1) + " from sql9281675.box" + serial_ID + ";";
                        System.out.println(query);
                        stmt = conn.createStatement();
                        ResultSet result = stmt.executeQuery(query);

                        while(result.next()){
                            String tmpinfo = result.getString("PillName"+(i+1));
                            if(tmpinfo != null){
                                info += (tmpinfo + ",");
                            }
                        }
                    }
                    i ++;
                }
                String[] words = info.split(",");

                for(int j = 0; j < words.length; j++){
                    final_info += words[j];
                    if(j < (words.length-2)){
                        final_info += ", ";
                    }
                    if(j == (words.length-2)){
                        final_info += " and ";
                    }
                }
                System.out.println(final_info);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    stmt.close();
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
        return final_info;
    }
}
