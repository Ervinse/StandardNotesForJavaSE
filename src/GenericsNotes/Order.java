package GenericsNotes;

public class Order <E>{
    E filed;

    public E getFiled() {
        return filed;
    }

    public void setFiled(E filed) {
        this.filed = filed;
    }

    public E method(E i){
        System.out.println(this.getClass());
        return i;
    }
    public <K> E GenericsMethod(E i){

        System.out.println(this.getClass());
        return i;
    }
}
