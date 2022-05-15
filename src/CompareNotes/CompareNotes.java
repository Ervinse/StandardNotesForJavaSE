package CompareNotes;

import java.util.Arrays;

public class CompareNotes {

    public static void main(String[] args) {

        String[] goodsName = new String[]{"B","C","D","A","G"};

        /*
        对于像String、包装类等已经实现了Comparable接口的类
        只需要通过Arrays方法类中的sort方法对该数组进行排序
        然后在输出语句中调用Arrays方法类中被重写的tostring方法
        即可输出排序后的数组
         */
        Arrays.sort(goodsName);
        System.out.println(Arrays.toString(goodsName));


        Goods[] goods = new Goods[5];
        goods [0] = new Goods("B",15);
        goods [1] = new Goods("C",20);
        goods [2] = new Goods("D",15);
        goods [3] = new Goods("A",35);
        goods [4] = new Goods("G",10);

        /*
        对于自定义类，我们可以让自定义类实现Comparable接口
        重写compareTo(obj)方法，在compareTo(obj)方法中指明如何排序
         */

        //通过Arrays方法类中的sort方法对该数组进行排序
        Arrays.sort(goods);

        System.out.println(Arrays.toString(goods));

    }
}

class Goods implements Comparable{
    String name;
    int price;

    public Goods() {
    }

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "price='" + price + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Goods){
            Goods goods = (Goods)o;
            //比较当前商品和下一个商品的价格
            if(this.price > goods.price){
                return 1;//当前商品价格贵，返回1
            }else if(this.price < goods.price){
                return -1;//下一个商品价格贵，返回-1
            }else{
                //价格一样比较名字顺序，String类的compareTo方法已经定义，直接调用
                return this.name.compareTo(goods.name);
            }
        }

        return 0;
    }
}