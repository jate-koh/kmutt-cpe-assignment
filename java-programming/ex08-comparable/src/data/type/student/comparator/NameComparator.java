package data.type.student.comparator;

import data.type.student.Student;
import java.util.Comparator;

public class NameComparator implements StudentComparator {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getFirstName().compareTo(o2.getFirstName()) > 0) return 1;
        else if (o1.getFirstName().compareTo(o2.getFirstName()) < 0) return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
