package Dao;
/**
 * 该类负责获取一个数据库连接
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://120.78.139.130:3306/orangeqq?useSSL=false&characterEncoding=UTF8&serverTimezone=Asia/Shanghai";
    public static final String DBUSER = "juzi";
    public static final String DBPASSWORD = "jqsmx1731815301";
    public static Connection con = null;

    static {
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("加载JDBC驱动失败！");
        }
    }

    public Connection connection() throws Exception {
        con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        return con;
    }
}
