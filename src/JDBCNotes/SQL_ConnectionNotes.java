package JDBCNotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SQL_ConnectionNotes {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //将数据库连接的4个基本信息申明在配置文件中，通过读取配置文件的方式获取
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String urlProperties = resourceBundle.getString("url");
        String userProperties = resourceBundle.getString("user");
        String passwordProperties = resourceBundle.getString("password");
        String driverClassProperties = resourceBundle.getString("driverClass");

        //加载驱动
        Class.forName(driverClassProperties);

        //获取连接
        Connection conn = DriverManager.getConnection(urlProperties, userProperties, passwordProperties);

        System.out.println(conn);//com.mysql.cj.jdbc.ConnectionImpl@6a2bcfcb

        /*
            备注：
            模块需要添加驱动文件：driverClassProperties（文件->项目结构->加号->Java）
            jdbcProperties需要放在src目录下

         */
    }
}
