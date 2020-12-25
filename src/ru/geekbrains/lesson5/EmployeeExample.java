package ru.geekbrains.lesson5;

public class EmployeeExample {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("John Doe", "sales manager", "johndoe@example.com", "8 (800) 888-88-88", 60000, 58);
        employees[1] = new Employee("Martha Simmons", "CEO", "marthasimmons@example.com", "8 (800) 777-77-77", 120000, 41);
        employees[2] = new Employee("Andy Carson", "courier", "courier@example.com", "8 (999) 999-99-99", 15000, 19);
        employees[3] = new Employee("Bette Mallory", "secretary", "betty@example.com", "8 (800) 555-55-55", 40000, 25);
        employees[4] = new Employee("Vic Sandberg", "janitor","cleaningservice@example.com", "8 (800) 111-11-11", 20000, 50);

        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                employee.showInfo();
            }
        }
    }
}
