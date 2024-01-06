package org.example;

public class User {

    private String email;
    private String firstName;
    private long age;

    public User() {
    }

    public User(String email, String firstName, long age) {
        this.email = email;
        this.firstName = firstName;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
