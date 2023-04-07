package data.structure.stack;

import data.type.student.Student;
import data.type.student.comparator.StudentComparator;
import utils.Logger;

public class StudentStack extends Stack<Student>{

    public StudentStack() {
        super();
    }

    public StudentStack(int size) {
        super(size);
    }

    public void sort(StudentComparator comparator) {
        for (int i = 0; i < this.items.size(); i++) {
            for (int j = i + 1; j < this.items.size(); j++) {
                if (comparator.compare((Student) this.items.get(i), (Student) this.items.get(j)) > 0) {
                    Student temp = (Student) this.items.get(i);
                    this.items.set(i, this.items.get(j));
                    this.items.set(j, temp);
                }
            }
        }
    }

    @Override
    public void printLog() {
        StringBuilder message = new StringBuilder("Stack: [");
        for (Object itr: this.items) {
            if( itr instanceof Student) {
                message.append(" { ")
                        .append(((Student) itr).getId()).append(", ")
                        .append(((Student) itr).getFirstName()).append(" ")
                        .append(((Student) itr).getLastName()).append(", ")
                        .append(((Student) itr).getGpa())
                    .append(" }, ");
            }
            else {
                message.append(itr);
            }
        }
        message.append("]");
        Logger.logMessage(message.toString(), "StudentStack");
    }

}
