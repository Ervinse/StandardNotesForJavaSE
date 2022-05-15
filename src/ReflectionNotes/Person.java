package ReflectionNotes;

public class Person extends Creature{

    public int publicSubField;
    private int privateSubField;

    public Person() {
    }

    public Person(int publicSupperField, int privateSupperField, int publicSubField, int privateSubField) {
        super(publicSupperField, privateSupperField);
        this.publicSubField = publicSubField;
        this.privateSubField = privateSubField;
    }

    public int getPublicSubField() {
        return publicSubField;
    }

    public void setPublicSubField(int publicSubField) {
        this.publicSubField = publicSubField;
    }

    public int getPrivateSubField() {
        return privateSubField;
    }

    public void setPrivateSubField(int privateSubField) {
        this.privateSubField = privateSubField;
    }
    public int subMethod(int field){
        System.out.println("subMethod");
        return field;
    }

    public static void subStaticMethod(int field){
        System.out.println("subStaticMethod");
    }

    @Override
    public String toString() {
        return "Person{" +
                "publicSupperField=" + publicSupperField +
                ", publicSubField=" + publicSubField +
                ", privateSubField=" + privateSubField +
                '}';
    }
}
