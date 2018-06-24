package Dao;
/**
 * 用户登录
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public static String username;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;

    public boolean login(Connection con, String username, String password) throws Exception {
        this.username=username;
        String sql = "select * from user where username=? and password=?";
        String sql1 = "UPDATE user SET status = 1 WHERE username = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setString(1, username);
            pstmt1.executeUpdate();
            return true;
        } else {
            return false;
        }

    }
}
