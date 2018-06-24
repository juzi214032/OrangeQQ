package controller;
/**
 * 处理登录相关操作
 */

import Bean.MyClient;
import Dao.DBCon;
import Dao.Login;

import java.sql.Connection;

public class Login_c {
    public static boolean login(String username,String password){
        //创建连接对象
        Connection con = null;
        try {
            //获取连接对象
            con = new DBCon().connection();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try {
            //如果登录成功
            if(new Login().login(con, username, password)) {
                //取得自己的ID
                MyClient.myId=username;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
