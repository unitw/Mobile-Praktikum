/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_DatenBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tw
 */
public class FBS_DatenBank {

    //  Database credentials
    String userName = "sa";
    String password = "EN5_20I6";

    String url = "jdbc:sqlserver://176.9.147.155;databaseName=FBS_Datenbank";
    //  Connection con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;databaseName=aysha", "user=sa", "password=admin");
    Connection conn = null;
    Statement stmt = null;

    public void connect() {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection(url, userName, password);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            sql = "select * from benutzer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String b_name = rs.getString("b_name");
                String b_kennungwort = rs.getString("b_kennwort");
                int b_lvl = rs.getInt("b_lvl");

                //Display values
                System.out.print("ID: " + b_name);
                System.out.print(", Age: " + b_kennungwort);
                System.out.print(", First: " + b_lvl);

            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            System.out.println("Goodbye!");
        }
    }

    public boolean login(String user, String pw) throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        conn = DriverManager.getConnection(url, userName, password);

        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;

//        sql = "select distinct from benutzer where" + "b_name=" + user + "b_kennwort=" + pw;
//        
        sql = "select * from benutzer where b_name='" + user + "' and b_kennwort='" + pw + "';";

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {

            String b_name = rs.getString("b_name");
            String b_kennwort = rs.getString("b_kennwort");

            b_kennwort = b_kennwort.replaceAll(" ", "");
            b_name = b_name.replaceAll(" ", "");

            if (b_name.equals(b_name) && b_kennwort.equals(pw)) {
                return true;
            }

        }
        rs.close();
        stmt.close();
        conn.close();
        return false;
    }

    public boolean register(String user, String pw) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        conn = DriverManager.getConnection(url, userName, password);
      
        PreparedStatement prepstmt = null;
//        if (!login(user, pw)) {
           

            int runde=0;
            int lvl=0;
            int gold=0;
            int edelsteine=0;
            
            prepstmt = conn.prepareStatement("insert into benutzer(b_name,b_kennwort,b_lvl,b_edelsteine,b_runde,b_gold) values (?,?,?,?,?,?)");
            prepstmt.setString(1, user);
            prepstmt.setString(2, pw);
            prepstmt.setInt(3, runde);
            prepstmt.setInt(4, lvl);
            prepstmt.setInt(5, gold);
            prepstmt.setInt(6, edelsteine);
            prepstmt.executeUpdate();

//            (deptnum, deptname, deptloc)
//            
//           INSERT INTO Benutzer VALUES (' user','  pw ', 1,0,0,0 );
//            stmt = conn.createStatement();
//            stmt.executeUpdate(sql);
           

//        }

        prepstmt.close();
        conn.close();
        return false;

    }

    public boolean saveround() {
        return false;
    }

}
