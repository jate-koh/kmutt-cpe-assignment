package data.type.student.comparator;

import data.type.student.Student;
import java.util.Comparator;

public interface StudentComparator extends Comparator<Student> {

    public int compare(Student o1, Student o2);

    public boolean equals(Object obj);

}
