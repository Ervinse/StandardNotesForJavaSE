package GenericsNotes;

//是泛型类
//SubGenericsOrder是泛型类，继承于Order<V>
// 其中V是根据实例化SubGenericsOrder对象时确定的泛型类型而确定的
public class SubGenericsOrder<V> extends Order<V>{
    public void subGenericsMethod(){
        System.out.println(super.getFiled());
        System.out.println(super.getFiled().getClass());
    }
}
