package pl.polsl.model;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;


public class Employee {
    private int workYear;
    private String experienceLevel;


    private String employmentType;
    private String jobTitle;
    private int salary;


    private String salaryCurrency;
    private int salaryInUsd;



    private String employeeResidence;



    private int remoteRatio;
    private String companyLocation;
    private String companySize;

    public Employee(int workYear, String experienceLevel, String employmentType, String jobTitle,
                    int salary, String salaryCurrency, int salaryInUsd, String employeeResidence,
                    int remoteRatio, String companyLocation, String companySize) {
        this.workYear = workYear;
        this.experienceLevel = experienceLevel;
        this.employmentType = employmentType;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.salaryInUsd = salaryInUsd;
        this.employeeResidence = employeeResidence;
        this.remoteRatio = remoteRatio;
        this.companyLocation = companyLocation;
        this.companySize = companySize;
    }
    public Employee() {

    }
    // Metoda zwracająca informacje o zarobkach w postaci stringa
    public String getHighestPaidJobTitle(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return "No employee data available.";
        }

        // Grupowanie pracowników po stanowisku i sumowanie ich zarobków
        Map<String, Integer> jobTitleToTotalSalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getJobTitle,
                        Collectors.summingInt(Employee::getSalary)));

        // Znalezienie stanowiska z najwyższą sumą zarobków
        String highestPaidJobTitle = jobTitleToTotalSalary.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No highest paid job title found.");

        return highestPaidJobTitle;
    }
    public void sortCurrenciesByEmploymentType(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employee data available.");
            return;
        }

        // Grupowanie pracowników według rodzaju zatrudnienia
        Map<String, List<Employee>> employeesByEmploymentType = employees.stream()
                .collect(Collectors.groupingBy(Employee::getEmploymentType));

        // Wyświetlanie posortowanych walut dla każdego rodzaju zatrudnienia
        employeesByEmploymentType.entrySet().forEach(entry -> {
            String employmentType = entry.getKey();
            List<Employee> employeesOfType = entry.getValue();

            // Sortowanie walut dla danego rodzaju zatrudnienia
            List<String> sortedCurrencies = employeesOfType.stream()
                    .map(Employee::getSalaryCurrency)
                    .distinct() // Eliminacja duplikatów
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("Employment Type: " + employmentType);
            System.out.println("Sorted Currencies: " + sortedCurrencies);
            System.out.println("--------------------");
        });
    }

    public String getMostFrequentSalaryCurrency(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return "No employee data available.";
        }

        // Grupowanie pracowników po walucie i zliczanie wystąpień
        Map<String, Long> currencyToCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalaryCurrency, Collectors.counting()));

        // Znalezienie waluty z największą liczbą wystąpień
        String mostFrequentCurrency = currencyToCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No salary currency found.");

        return mostFrequentCurrency;
    }

    public double calculatePearsonCorrelation(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return Double.NaN; // Korelacja nieokreślona dla pustego zbioru danych
        }

        double[] residenceArray = employees.stream()
                .mapToDouble(employee -> convertResidenceToNumeric(employee.getEmployeeResidence()))
                .toArray();

        double[] salaryInUsdArray = employees.stream()
                .mapToDouble(Employee::getSalaryInUsd)
                .toArray();

        PearsonsCorrelation correlation = new PearsonsCorrelation();
        return correlation.correlation(residenceArray, salaryInUsdArray);
    }

    public void displayRemoteWorkRanking(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employee data available.");
            return;
        }

        // Grupowanie pracowników po lokalizacji firmy
        Map<String, List<Employee>> employeesByCompanyLocation = employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyLocation));

        // Obliczenia procentowego udziału pracowników pracujących zdalnie dla każdej lokalizacji
        Map<String, Double> remoteWorkPercentageByLocation = employeesByCompanyLocation.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                        calculateRemoteWorkPercentage(entry.getValue())));

        // Sortowanie lokalizacji według procentowego udziału pracowników pracujących zdalnie
        remoteWorkPercentageByLocation.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry ->
                        System.out.println("Company Location: " + entry.getKey() +
                                ", Remote Work Percentage: " + entry.getValue() + "%"));
    }

    // Metoda pomocnicza do obliczenia procentowego udziału pracowników pracujących zdalnie
    private double calculateRemoteWorkPercentage(List<Employee> employees) {
        long remoteWorkCount = employees.stream().filter(employee -> employee.getRemoteRatio() > 0).count();
        return (double) remoteWorkCount / employees.size() * 100;
    }




    // Metoda pomocnicza do konwersji employee_residence na wartość numeryczną
    private double convertResidenceToNumeric(String residence) {
        // Prosta implementacja; można dostosować w zależności od rzeczywistych danych
        return residence.hashCode(); // Przykład; implementacja może być bardziej zaawansowana
    }

    @Override
    public String toString() {
        return "Employee{" +
                "workYear=" + workYear +
                ", experienceLevel='" + experienceLevel + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", salaryCurrency='" + salaryCurrency + '\'' +
                ", salaryInUsd=" + salaryInUsd +
                ", employeeResidence='" + employeeResidence + '\'' +
                ", remoteRatio=" + remoteRatio +
                ", companyLocation='" + companyLocation + '\'' +
                ", companySize='" + companySize + '\'' +
                '}';
    }
    public String getJobTitle() {
        return jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }


    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public int getSalaryInUsd() {
        return salaryInUsd;
    }

    public String getEmployeeResidence() {
        return employeeResidence;
    }

    public int getRemoteRatio() {
        return remoteRatio;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }


}




