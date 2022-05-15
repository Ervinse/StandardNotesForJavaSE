package JDBCNotes;


/*
 * ORM编程思想  （object relational mapping）
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 *
 */
public class Student {

    int stuID;
    String stuName;
    String stuGender;
    String stuPhone;

    public Student() {
    }

    public Student(int stuID, String stuName, String stuGender, String stuPhone) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuPhone = stuPhone;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuID=" + stuID +
                ", stuName='" + stuName + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                '}';
    }
}
