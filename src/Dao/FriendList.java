package Dao;
/**
 *从数据库中查询当前在线好友
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendList {
    public String[] list() {
        String sql = "SELECT username FROM user WHERE status = 1";
        PreparedStatement pstmt = null;
        List<String> list = new ArrayList<>();

        try {
            pstmt = DBCon.con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            list.add("群聊");
            while (rs.next()) {
                String str = rs.getString("username");
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[list.size()]);
    }
}
