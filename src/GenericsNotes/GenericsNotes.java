package GenericsNotes;

public class GenericsNotes {
    public static void main(String[] args) {

        //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
        Order order1 = new Order();
        Order<Integer> order2 = new Order<Integer>();
        Order<String> order3 = new Order<String>();

        order2.setFiled(10);
        System.out.println(order2.getFiled());//10
        System.out.println(order2.getFiled().getClass());//class java.lang.Integer

        order3.setFiled("A");
        System.out.println(order3.getFiled());//A
        System.out.println(order3.getFiled().getClass());//class java.lang.String

        System.out.println("1*********************");

        SubOrder subOrder = new SubOrder();
        subOrder.setFiled(10);
        //由于SubOrder是继承于Order<Integer>,所以其父类属性field是Integer类型的
        subOrder.subMethod();
        //10
        //class java.lang.Integer

        System.out.println("2*********************");

        SubGenericsOrder<String> stringSubGenericsOrder = new SubGenericsOrder<String>();
        stringSubGenericsOrder.setFiled("A");
        //在实例化stringSubGenericsOrder对象时声明了其SubGenericsOrder类的泛型类型为String
        //因此stringSubGenericsOrder为继承于Order<String>类的对象
        stringSubGenericsOrder.subGenericsMethod();
    }
}
