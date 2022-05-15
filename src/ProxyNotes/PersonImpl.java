package ProxyNotes;

public class PersonImpl implements Person{
    @Override
    public int actionA(int num) {
        System.out.println("PersonImpl actionA() :" + num);
        return num;
    }

    @Override
    public int actionB(int num) {
        System.out.println("PersonImpl actionB() :" + num);
        return num;
    }
}
