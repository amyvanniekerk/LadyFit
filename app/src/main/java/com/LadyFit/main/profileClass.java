package com.LadyFit.main;

public class profileClass {

    String name;
    String email;
    String age;
    String dob;
    double height;
    double weight;

    public profileClass(){
    }

    public profileClass(String name, String email, String age, String dob, double height, double weight) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
    }

    public profileClass(String editName, String editEmail, String editPassword, String editAge, String editDob) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
