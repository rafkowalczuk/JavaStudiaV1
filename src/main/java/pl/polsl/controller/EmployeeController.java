package pl.polsl.controller;

import pl.polsl.model.CsvDataReader;
import pl.polsl.model.Employee;
import pl.polsl.view.EmployeeView;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private List<Employee> employees;
    private EmployeeView employeeView;

    public EmployeeController(String csvFilePath) {
        // Inicjalizacja danych z pliku CSV
        this.employees = CsvDataReader.readCsvData(csvFilePath);
        // Inicjalizacja widoku
        this.employeeView = new EmployeeView(employees);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Wyświetlanie menu
            System.out.println("Menu:");
            System.out.println("1. Wyświetl najwyżej opłacane stanowisko");
            System.out.println("2. Wyświetl posortowane waluty według rodzaju zatrudnienia");
            System.out.println("3. Wyświetl najczęściej występującą walutę wynagrodzenia");
            System.out.println("4. Wyświetl korelację Pearsona między miejscem zamieszkania a wynagrodzeniem w USD");
            System.out.println("5. Wyświetl ranking lokalizacji firm pod względem udziału pracowników pracujących zdalnie");
            System.out.println("0. Wyjdź z programu");

            // Odczytanie wyboru użytkownika
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            // Wykonanie odpowiedniej operacji na podstawie wyboru
            switch (choice) {
                case 1:
                    employeeView.displayHighestPaidJobTitle();
                    break;
                case 2:
                    employeeView.displaySortedCurrenciesByEmploymentType();
                    break;
                case 3:
                    employeeView.displayMostFrequentSalaryCurrency();
                    break;
                case 4:
                    employeeView.displayPearsonCorrelation();
                    break;
                case 5:
                    employeeView.displayRemoteWorkRanking();
                    break;
                case 0:
                    // Zakończenie programu
                    System.out.println("Koniec programu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
