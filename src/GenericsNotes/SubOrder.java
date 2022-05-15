package GenericsNotes;

//不是泛型子类
//SubOrder继承于泛型类型为Integer的Order类
public class SubOrder extends Order<Integer>{
    public void subMethod(){
        System.out.println(super.getFiled());
        System.out.println(super.getFiled().getClass());
    }
}
