package ru.geekbrains.lesson5;

public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private float salary;
    private int age;

    public Employee(String name, String position, String email, String phone, float salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void showInfo() {
        System.out.printf("%1s, %2d, is a %3s with salary $%4.2f. Contacts: email %5s, phone %6s.\n",
                name, age, position, salary, email, phone);
    }
}
