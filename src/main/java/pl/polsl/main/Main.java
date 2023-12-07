package pl.polsl.main;

import pl.polsl.controller.EmployeeController;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\rafko\\Downloads\\Cyber_salaries.csv";
        EmployeeController employeeController = new EmployeeController(csvFilePath);
        employeeController.run();
    }
}