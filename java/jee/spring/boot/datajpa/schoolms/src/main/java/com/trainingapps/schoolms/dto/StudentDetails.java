package com.trainingapps.schoolms.dto;

public class StudentDetails {
    private int id;
    private String name;
    private int age;
    private String course;

    public StudentDetails(){}

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public StudentDetails(int id, String name, int age, String course){
        this.id=id;
        this.name=name;
        this.age=age;
        this.course=course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
