package EnumNotes;

public class EnumNotes {
    public static void main(String[] args) {

        Season SPRING = Season.SPRING;
        Season SUMMER = Season.SUMMER;
        Season AUTUMN = Season.AUTUMN;
        Season WINTER= Season.SPRING;
        System.out.println(SPRING);
        System.out.println(SUMMER);
        System.out.println(AUTUMN);
        System.out.println(WINTER);

        System.out.println("***********************");

        Cat ORANGECAT = Cat.ORANGECAT;
        Cat BLACKCAT = Cat.BLACKCAT;
        Cat WHITECAT = Cat.WHITECAT;
        Cat GRAYCAT = Cat.GRAYCAT;
        //enum关键字定义枚举类已经重写了toString()方法
        System.out.println(ORANGECAT);//ORANGECAT
        System.out.println(BLACKCAT);//BLACKCAT
        System.out.println(WHITECAT);//WHITECAT
        System.out.println(GRAYCAT);//GRAYCAT

        System.out.println("***********************");

        Dog ORANGEDOG = Dog.ORANGEDOG;
        Dog BLACKDOG = Dog.BLACKDOG;
        Dog WHITEDOG = Dog.WHITEDOG;
        Dog GRAYDOG = Dog.GRAYDOG;
        System.out.println(ORANGEDOG);//ORANGEDOG
        System.out.println(BLACKDOG);//BLACKDOG
        System.out.println(WHITEDOG);//WHITEDOG
        System.out.println(GRAYDOG);//GRAYDOG

        ORANGEDOG.getInfo();
        BLACKDOG.getInfo();
        WHITEDOG.getInfo();
        GRAYDOG.getInfo();

        System.out.println("***********************");

        Status status = Status.BUSY;
        if(status == Status.BUSY){
            System.out.println("在忙");
        }else if(status == Status.FREE){
            System.out.println("空闲");
        }




    }
}

//自定义枚举类
class Season{
    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象：public static final的
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //4.其他诉求2：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

//使用enum关键字定义枚举类
//说明：使用enum关键字定义枚举类默认继承于java.lang.Enum类
enum Cat{
    //提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    ORANGECAT("A",2),BLACKCAT("B",3),WHITECAT("C",7),GRAYCAT("D",4);

    //声明Cat对象的属性:private final修饰
    private final String name;
    private final int age;

    //私有化类的构造器,并给对象属性赋值
    Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //提供获取枚举类对象的属性的方法
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}

//不同对象重写接口中的方法
interface Information{
    void getInfo();
}

enum Dog implements Information{
    //提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    ORANGEDOG("A",2){
        @Override
        public void getInfo() {
            System.out.println("name = " + getName() +"\tage = " + getAge() + "\tline1");
        }
    },BLACKDOG("B",3) {
        @Override
        public void getInfo() {
            System.out.println("name = " + getName() +"\tage = " + getAge() + "\tline2");
        }
    },WHITEDOG("C",7) {
        @Override
        public void getInfo() {
            System.out.println("name = " + getName() +"\tage = " + getAge() + "\tline3");
        }
    },GRAYDOG("D",4) {
        @Override
        public void getInfo() {
            System.out.println("name = " + getName() +"\tage = " + getAge() + "\tline4");
        }
    };

    //声明Dog对象的属性:private final修饰
    private final String name;
    private final int age;

    //私有化类的构造器,并给对象属性赋值
    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //提供获取枚举类对象的属性的方法
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}
//简单枚举类
enum Status{
    FREE,BUSY,BUSINESS,VACATION;
}

