import data.type.student.Student;
import data.structure.stack.StudentStack;
import data.type.student.comparator.*;
import gui.MainPanel;

public class Main {
    public static void main(String[] args)  {

        // Initialize a stack
        StudentStack stack = new StudentStack(10);

        // Initialize comparator
        StudentComparator gpaComparator = new GpaComparator();
        StudentComparator idComparator = new IdComparator();
        StudentComparator nameComparator = new NameComparator();
        StudentComparator lastnameComparator = new LastNameComparator();

        // Initialize some students
        stack.push(new Student("101","Hilly","Billy",3.5));
        stack.push(new Student("10001","Billy","Silly",2.5));
        stack.push(new Student("101","Lilly","Silly",1.5));
        stack.push(new Student("101","Silly","Hilly",3.0));
        stack.push(new Student("1007", "Keeratikorn", "Aonrok", 2.5));
        stack.push(new Student("1002", "Porama", "Ohm", 3.5));
        stack.printLog();

        // Initialize a main panel
        MainPanel panel = new MainPanel(stack);

    }

}