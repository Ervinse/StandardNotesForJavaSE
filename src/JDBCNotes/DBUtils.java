package JDBCNotes;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class DBUtils {
    public static void main(String[] args) throws Exception {


        //获取连接
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();

        //插入
        String sql1 = "insert into student (stuID,stuName,stuGender,stuPhone) values(?,?,?,?);";
        int insertCount = runner.update(conn, sql1, 4, "BB", "女", "123456");
        System.out.println("插入了" + insertCount + "条数据");

        //单条查询
        //BeanHandler是ResultSetHandler接口的实现类，用于封装表中的一条记录
        String sql2 = "select * from student where stuID = ?";
        BeanHandler<Student> studentBeanHandler = new BeanHandler<>(Student.class);
        Student studentResult1 = runner.query(conn, sql2, studentBeanHandler, 1);
        System.out.println("查询到以下内容：");
        System.out.println(studentResult1);

        //多条查询
        //BeanListHandler是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合
        String sql3 = "select * from student where stuID < ?";
        BeanListHandler<Student> studentBeanListHandler = new BeanListHandler<>(Student.class);
        List<Student> studentResult2 = runner.query(conn, sql3, studentBeanListHandler, 4);
        System.out.println("查询到以下内容：");
        studentResult2.forEach(System.out::println);
    }
}
