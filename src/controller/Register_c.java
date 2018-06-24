package controller;

import Dao.DBCon;
import Dao.Register;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 处理注册相关操作
 */
public class Register_c {
    public static int regist(String username, String password, String password_r) {
        Connection con = null;
        try {
            con = new DBCon().connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //判断账号是否为空，如果为空返回3
            if (" 账号（8-15位字母或数字）".equals(username) | " 格式错误！".equals(username)) {
                return 3;
                //判断密码是否为空，如果为空返回4
            } else if (" 输入密码（不少于10位）".equals(password)) {
                return 4;
                //判断两次密码是否相同，不相同返回2
            } else if (!password.equals(password_r)) {
                return 2;
                //所有验证均通过，返回0
            } else {
                new Register().register(con, username, password);
                con.close();
                return 0;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            //账号重复
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("返回了0");
        return 0;
    }
}
