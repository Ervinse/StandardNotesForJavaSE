package JDBCNotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PreparedStatementNotes {
    public static void main(String[] args) {
        Connection conn = null;
        java.sql.PreparedStatement statement = null;

        try {
            //将数据库连接的4个基本信息申明在配置文件中，通过读取配置文件的方式获取
            ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
            String urlProperties = resourceBundle.getString("url");
            String userProperties = resourceBundle.getString("user");
            String passwordProperties = resourceBundle.getString("password");
            String driverClassProperties = resourceBundle.getString("driverClass");

            //加载驱动
            Class.forName(driverClassProperties);

            //获取连接
            conn = DriverManager.getConnection(urlProperties, userProperties, passwordProperties);

            //预编译sql语句，返回PreparedStatement实例
            String sql = "INSERT INTO student (stuID,stuName,stuGender,stuPhone) VALUES(?,?,?,?)";
            statement = conn.prepareStatement(sql);

            //填充占位符
            statement.setInt(1,1);
            statement.setString(2,"AA");
            statement.setString(3,"男");
            statement.setString(4,"1234");

            //执行操作
            statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {

            //关闭资源
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
