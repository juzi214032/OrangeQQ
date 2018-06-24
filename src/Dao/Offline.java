package Dao;
/**
 * 用户下线
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Offline {
    public void offline(){

        String sql = "UPDATE user SET status = 0 WHERE username = ?";
        try {
            PreparedStatement pstmt = DBCon.con.prepareStatement(sql);
            pstmt.setString(1, Login.username);
            pstmt.executeUpdate();
            //关闭连接
            DBCon.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
