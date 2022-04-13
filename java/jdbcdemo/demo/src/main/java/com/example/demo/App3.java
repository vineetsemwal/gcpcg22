/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.demo;

//import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App3 {
    private Connection connection;

    public static void main(String[] args) throws Exception {
        App3 demo = new App3();
        demo.doExecute();
    }

    public void doExecute() throws SQLException, ClassNotFoundException, StudentNotFoundException {
        //  Driver driver=new Driver();
        //     DriverManager.registerDriver(driver);
        //step1 register driver
        Class.forName("org.postgresql.Driver");

        //step2 open connection
        String url = "jdbc:postgresql://localhost:5432/training";
        String user = "postgres", password = "scooby";

        connection = DriverManager.getConnection(url, user, password);

        Student student1 = new Student();
        student1.setId(1);
        student1.setUsername("animesh");
        student1.setAge(21);
        insert(student1);
        long id = student1.getId();

        Student foundStudent = findById(id);
        displayStudent(foundStudent);

        List<Student> list = fetchStudents();
        displayStudents(list);
        connection.close();

    }

    public void insert(Student student) throws SQLException {
        String insertQueryText = "insert into students(id, username,age) values(?,?,?)";
        PreparedStatement statement=connection.prepareStatement(insertQueryText);
        statement.setLong(1,student.getId());
        statement.setString(2, student.getUsername());
        statement.setInt(3,student.getAge());
        int rowsUpdated = statement.executeUpdate();
        System.out.println("rows updated=" + rowsUpdated);

    }


    public Student findById(long id) throws SQLException, StudentNotFoundException {
        String fetchQueryText = "select * from students where id=?" ;
        PreparedStatement statement=connection.prepareStatement(fetchQueryText);
        statement.setLong(1,id);
        ResultSet resultSet = statement.executeQuery();
        Student student = new Student();
        if (!resultSet.next()) {
            throw new StudentNotFoundException("student not found for id=" + id);
        }
        String username = resultSet.getString("username");
        int age = resultSet.getInt("age");
        student.setId(id);
        student.setAge(age);
        student.setUsername(username);
        return student;

    }


    public List<Student> fetchStudents() throws SQLException {
        String fetchQueryText = "select * from students";
        PreparedStatement statement=connection.prepareStatement(fetchQueryText);
        ResultSet resultSet = statement.executeQuery();
        List<Student> students = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            int age = resultSet.getInt("age");
            System.out.println("id=" + id + " username=" + username + " age=" + age);
            Student student = new Student();
            student.setId(id);
            student.setUsername(username);
            student.setAge(age);
            students.add(student);
        }
        return students;

    }

    public void displayStudent(Student student) {
        System.out.println("student-" + student.getId() + ", " + student.getUsername() + " ," + student.getAge());
    }

    public void displayStudents(Collection<Student> students) {
        for (Student student : students) {
            displayStudent(student);
        }
    }


}