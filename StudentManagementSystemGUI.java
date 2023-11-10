package com.example.codsoft;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystemGUI extends Application {

    private StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add UI components
        TextField nameTextField = new TextField();
        TextField rollNumberTextField = new TextField();
        TextField gradeTextField = new TextField();
        Button addButton = new Button("Add Student");
        Button editButton = new Button("Edit Student");
        Button searchButton = new Button("Search Student");
        Button displayAllButton = new Button("Display All Students");
        Button exitButton = new Button("Exit");
        TextArea displayArea = new TextArea();

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Roll Number:"), 0, 1);
        grid.add(rollNumberTextField, 1, 1);
        grid.add(new Label("Grade:"), 0, 2);
        grid.add(gradeTextField, 1, 2);
        grid.add(addButton, 1, 3);
        grid.add(editButton, 2, 3);
        grid.add(searchButton, 1, 4);
        grid.add(displayAllButton, 2, 4);
        grid.add(exitButton, 1, 5);
        grid.add(displayArea, 0, 6, 3, 1);

        // Event handling
        addButton.setOnAction(e -> {
            if (validateInput(nameTextField.getText(), rollNumberTextField.getText(), gradeTextField.getText())) {
                String name = nameTextField.getText();
                String rollNumber = rollNumberTextField.getText();
                String grade = gradeTextField.getText();

                // Add the student
                studentManagementSystem.addStudent(new Student(name, rollNumber, grade));

                // Display all students
                displayArea.setText(studentManagementSystem.displayAllStudents());
            } else {
                displayArea.setText("Invalid input. Please fill in all fields.");
            }
        });

        editButton.setOnAction(e -> {
            String rollNumber = rollNumberTextField.getText();
            if (!rollNumber.isEmpty()) {
                Student studentToEdit = studentManagementSystem.searchStudent(rollNumber);
                if (studentToEdit != null) {
                    // Perform editing logic here
                    displayArea.setText("Editing student: " + studentToEdit.toString());
                } else {
                    displayArea.setText("Student with Roll Number " + rollNumber + " not found.");
                }
            } else {
                displayArea.setText("Please enter Roll Number for editing.");
            }
        });

        searchButton.setOnAction(e -> {
            String rollNumber = rollNumberTextField.getText();
            if (!rollNumber.isEmpty()) {
                Student searchedStudent = studentManagementSystem.searchStudent(rollNumber);
                if (searchedStudent != null) {
                    displayArea.setText("Student found: " + searchedStudent.toString());
                } else {
                    displayArea.setText("Student with Roll Number " + rollNumber + " not found.");
                }
            } else {
                displayArea.setText("Please enter Roll Number for searching.");
            }
        });

        displayAllButton.setOnAction(e -> {
            // Display all students
            displayArea.setText(studentManagementSystem.displayAllStudents());
        });

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private boolean validateInput(String name, String rollNumber, String grade) {
        return !name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty();
    }
}

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String displayAllStudents() {
        StringBuilder result = new StringBuilder();
        for (Student student : students) {
            result.append(student.toString()).append("\n");
        }
        return result.toString();
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }
}
