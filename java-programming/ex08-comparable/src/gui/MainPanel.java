package gui;

import data.structure.stack.StudentStack;
import data.type.student.Student;
import utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame implements ActionListener {

    private StackPanel stackPanel;

    // Elements
    private JButton popButton;
    private JButton pushButton;
    private JButton sortButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField idField;
    private JTextField gpaField;
    private JCheckBox sortGpaCheckBox;
    private JCheckBox sortIdCheckBox;
    private JCheckBox sortLastNameCheckBox;
    private JCheckBox sortFirstNameCheckBox;

    public MainPanel() {
        this(new StudentStack());
    }

    public MainPanel(StudentStack stack) {
        this.stackPanel = new StackPanel();
        this.stackPanel.setStack(stack);
        this.init();
    }

    public void init() {
        this.setTitle("Student Stack");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initElements();
        this.initLayout();
    }

    private void initElements() {
        this.popButton = new JButton("Pop");
        this.pushButton = new JButton("Push");
        this.sortButton = new JButton("Sort");
        this.firstNameField = new JTextField();
        this.lastNameField = new JTextField();
        this.idField = new JTextField();
        this.gpaField = new JTextField();
        this.sortGpaCheckBox = new JCheckBox("GPA");
        this.sortIdCheckBox = new JCheckBox("ID");
        this.sortLastNameCheckBox = new JCheckBox("Last Name");
        this.sortFirstNameCheckBox = new JCheckBox("First Name");
    }

    private void initStackDisplay() {
        this.stackPanel.drawStack();
    }

    private void initLayout() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));
        formPanel.add(new JLabel("First Name"));
        formPanel.add(this.firstNameField);
        formPanel.add(new JLabel("Last Name"));
        formPanel.add(this.lastNameField);
        formPanel.add(new JLabel("ID"));
        formPanel.add(this.idField);
        formPanel.add(new JLabel("GPA"));
        formPanel.add(this.gpaField);
        this.add(formPanel, BorderLayout.NORTH);

        this.stackPanel.drawStack();
        this.add(this.stackPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4));
        buttonPanel.add(this.sortFirstNameCheckBox);
        buttonPanel.add(this.sortLastNameCheckBox);
        buttonPanel.add(this.sortGpaCheckBox);
        buttonPanel.add(this.sortIdCheckBox);
        buttonPanel.add(this.popButton);
        buttonPanel.add(this.pushButton);
        buttonPanel.add(this.sortButton);
        buttonPanel.setVisible(true);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.sortFirstNameCheckBox.addActionListener(this);
        this.sortLastNameCheckBox.addActionListener(this);
        this.sortGpaCheckBox.addActionListener(this);
        this.sortIdCheckBox.addActionListener(this);
        this.popButton.addActionListener(this);
        this.pushButton.addActionListener(this);
        this.sortButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // Push Button
        if (e.getSource() == this.pushButton) {

            // Check text field for empty
            if (this.firstNameField.getText().isEmpty() || this.lastNameField.getText().isEmpty() || this.idField.getText().isEmpty() || this.gpaField.getText().isEmpty()) {
                Logger.logMessage("Text Fields are empty", this);
                return;
            }

            Logger.logMessage("Push Button Clicked", this);
            String firstName = this.firstNameField.getText();
            String lastName = this.lastNameField.getText();
            String id = this.idField.getText();
            double gpa = Double.parseDouble(this.gpaField.getText());
            Logger.logMessage("Adding Student: {" + firstName + " " + lastName + " " + id + " " + gpa + " }", this);
            Student student = new Student(id, firstName, lastName, gpa);
            this.stackPanel.addStudent(student);
            this.stackPanel.getStack().printLog();
            this.stackPanel.refresh();

            // Clear Fields
            this.firstNameField.setText("");
            this.lastNameField.setText("");
            this.idField.setText("");
            this.gpaField.setText("");
        }

        if(e.getSource() == this.popButton) {
            Logger.logMessage("Pop Button Clicked", this);
            this.stackPanel.removeStudent();
            this.stackPanel.getStack().printLog();
            this.stackPanel.refresh();
        }

        if(e.getSource() == this.sortButton) {

            // Check if any sort is selected
            if(this.sortFirstNameCheckBox.isSelected() ||
                    this.sortLastNameCheckBox.isSelected() ||
                    this.sortGpaCheckBox.isSelected() ||
                    this.sortIdCheckBox.isSelected()
            ) {
                if (this.sortFirstNameCheckBox.isSelected()) {
                    Logger.logMessage("Sort: Firstname", this);
                    this.stackPanel.sortByFirstName();
                    this.stackPanel.getStack().printLog();
                    this.stackPanel.refresh();
                } else if (this.sortLastNameCheckBox.isSelected()) {
                    Logger.logMessage("Sort: Lastname", this);
                    this.stackPanel.sortByLastName();
                    this.stackPanel.getStack().printLog();
                    this.stackPanel.refresh();
                } else if (this.sortGpaCheckBox.isSelected()) {
                    Logger.logMessage("Sort: GPA", this);
                    this.stackPanel.sortByGpa();
                    this.stackPanel.getStack().printLog();
                    this.stackPanel.refresh();
                } else if (this.sortIdCheckBox.isSelected()) {
                    Logger.logMessage("Sort: ID", this);
                    this.stackPanel.sortById();
                    this.stackPanel.getStack().printLog();
                    this.stackPanel.refresh();
                }
            } else {
                Logger.logMessage("No sort selected", this);
            }
        }
    }
}
