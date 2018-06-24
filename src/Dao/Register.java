package Dao;
/**
 * 用户注册
 */

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register {
    PreparedStatement pstmt = null;
    public void register(Connection con,String username,String password) throws Exception {
        String sql = "insert into user (username,password) values (?,?)";
        pstmt=con.prepareStatement(sql);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        pstmt.executeUpdate();
    }
}
