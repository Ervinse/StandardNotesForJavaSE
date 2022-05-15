package ReflectionNotes;

public class Creature {
    public int publicSupperField;
    private int privateSupperField;

    public Creature() {
    }

    public Creature(int publicSupperField, int privateSupperField) {
        this.publicSupperField = publicSupperField;
        this.privateSupperField = privateSupperField;
    }

    public int getPublicSupperField() {
        return publicSupperField;
    }

    public void setPublicSupperField(int publicSupperField) {
        this.publicSupperField = publicSupperField;
    }

    public int getPrivateSupperField() {
        return privateSupperField;
    }

    public void setPrivateSupperField(int privateSupperField) {
        this.privateSupperField = privateSupperField;
    }
}
