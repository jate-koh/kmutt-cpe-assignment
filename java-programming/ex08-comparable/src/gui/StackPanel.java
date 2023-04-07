package gui;

import javax.swing.*;
import java.awt.*;

import data.structure.stack.StudentStack;
import data.type.student.*;
import data.type.student.comparator.*;

public class StackPanel extends JInternalFrame {

    private StudentStack stack;
    private JTable table;

    public StackPanel() {
        this(200, 200, new StudentStack());
    }

    public StackPanel(StudentStack stack) {
        this(200, 200, stack);
    }

    public StackPanel(int width, int height) {
        this(width, height, new StudentStack());
    }

    public StackPanel(int width, int height, StudentStack stack) {
        this.setSize(width, height);
        this.setTitle("Student Stack Table");
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setStack(stack);
    }

//    public void drawStack() {
//        JPanel stackPanel = new JPanel();
//        stackPanel.setLayout(new GridLayout(this.stack.getSize(), 1));
//        for (int i = 0; i < this.stack.getSize(); i++) {
//            Student student = this.stack.get(i);
//            JLabel label = new JLabel(
//                student.getFirstName()
//                + " " + student.getLastName()
//                + " " + student.getId()
//                + " " + student.getGpa()
//            );
//            stackPanel.add(label);
//        }
//        this.add(stackPanel, BorderLayout.CENTER);
//    }

    public void drawStack() {
        table = new JTable(this.stack.getSize() + 1, 4);

        // Set Table Headers
        table.setValueAt("First Name", 0, 0);
        table.setValueAt("Last Name", 0, 1);
        table.setValueAt("ID", 0, 2);
        table.setValueAt("GPA", 0, 3);

        // Set Table Content
        for (int i = 0; i < this.stack.getSize(); i++) {
            Student student = this.stack.get(i);
            table.setValueAt(student.getFirstName(), i + 1, 0);
            table.setValueAt(student.getLastName(), i + 1, 1);
            table.setValueAt(student.getId(), i + 1, 2);
            table.setValueAt(student.getGpa(), i + 1, 3);
        }
        this.add(table, BorderLayout.CENTER);
    }

    public void refresh() {
        this.remove(table);
        this.drawStack();
        this.repaint();
        this.add(table, BorderLayout.CENTER);
    }

    public StudentStack getStack() {
        return stack;
    }

    public void setStack(StudentStack stack) {
        this.stack = stack;
    }

    public void addStudent(Student student) {
        this.stack.push(student);
    }

    public void removeStudent() {
        this.stack.pop();
    }

    public void sortByFirstName() {
        StudentComparator comparator = new NameComparator();
        this.stack.sort(comparator);
    }

    public void sortByLastName() {
        StudentComparator comparator = new LastNameComparator();
        this.stack.sort(comparator);
    }

    public void sortById() {
        StudentComparator comparator = new IdComparator();
        this.stack.sort(comparator);
    }

    public void sortByGpa() {
        StudentComparator comparator = new GpaComparator();
        this.stack.sort(comparator);
    }

}

