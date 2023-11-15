import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if ( o1.getGPA() > o2.getGPA() ) return 1;
        else if ( o1.getGPA() < o2.getGPA() ) return -1;
        else return 0;
    }

    public boolean equals(Object obj) {
        if ( obj instanceof StudentComparator ) {
            return true;
        }
        else return false;
    }
}
