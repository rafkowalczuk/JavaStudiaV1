package pl.polsl.view;

import pl.polsl.model.Employee;

import java.util.List;

public class EmployeeView {
    private List<Employee> employees;

    public EmployeeView(List<Employee> employees) {
        this.employees = employees;
    }

    public void displayHighestPaidJobTitle() {
        System.out.println("Najwyżej opłacane stanowisko: " + new Employee().getHighestPaidJobTitle(employees));
    }

    public void displaySortedCurrenciesByEmploymentType() {
        System.out.println("Posortowane waluty według rodzaju zatrudnienia:");
        new Employee().sortCurrenciesByEmploymentType(employees);
    }

    public void displayMostFrequentSalaryCurrency() {
        System.out.println("Najczęściej występująca waluta wynagrodzenia: " + new Employee().getMostFrequentSalaryCurrency(employees));
    }

    public void displayPearsonCorrelation() {
        System.out.println("Korelacja Pearsona między miejscem zamieszkania a wynagrodzeniem w USD: " + new Employee().calculatePearsonCorrelation(employees));
    }

    public void displayRemoteWorkRanking() {
        System.out.println("Ranking lokalizacji firm pod względem udziału pracowników pracujących zdalnie:");
        new Employee().displayRemoteWorkRanking(employees);
    }
}
