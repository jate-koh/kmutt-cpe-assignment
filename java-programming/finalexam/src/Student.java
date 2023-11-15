public class Student {

    private String name;
    private String id;
    private double GPA;

    public Student(String name, String id, double GPA) {
        this.name = name;
        this.id = id;
        this.GPA = GPA;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public String  getId() {
        return id;
    }

    public double getGPA() {
        return GPA;
    }

}
