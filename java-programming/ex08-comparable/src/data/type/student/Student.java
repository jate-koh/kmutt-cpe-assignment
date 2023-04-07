package data.type.student;

public class Student implements Comparable {
    private String id;
    private String firstname;
    private String lastname;
    private double gpa;

    public Student(String id, String firstname, String lastname, double gpa) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Student) {
            Student s = (Student)o;
            if(this.gpa > s.gpa) return 1;
            else if(this.gpa < s.gpa) return -1;
            else return 0;
        }
        return 0;
    }
}
