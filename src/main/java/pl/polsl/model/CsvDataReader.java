package pl.polsl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {

    public static List<Employee> readCsvData(String csvFilePath) {
        List<Employee> employees = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader)) {

            for (CSVRecord csvRecord : csvParser) {
                try {
                    Employee employee = parseCsvRecord(csvRecord);
                    if (employee != null) {
                        employees.add(employee);
                    }
                } catch (Exception e) {
                    // Obsługa błędu walidacji CSV
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Obsługa błędu odczytu pliku
            e.printStackTrace();
        }

        return employees;
    }

    private static Employee parseCsvRecord(CSVRecord csvRecord) {
        try {
            // Parsujemy dane z rekordu CSV
            int workYear = Integer.parseInt(csvRecord.get("work_year"));
            String experienceLevel = csvRecord.get("experience_level");
            String employmentType = csvRecord.get("employment_type");
            String jobTitle = csvRecord.get("job_title");
            int salary = Integer.parseInt(csvRecord.get("salary"));
            String salaryCurrency = csvRecord.get("salary_currency");
            int salaryInUsd = Integer.parseInt(csvRecord.get("salary_in_usd"));
            String employeeResidence = csvRecord.get("employee_residence");
            int remoteRatio = Integer.parseInt(csvRecord.get("remote_ratio"));
            String companyLocation = csvRecord.get("company_location");
            String companySize = csvRecord.get("company_size");

            // Tworzymy i zwracamy obiekt Employee
            return new Employee(workYear, experienceLevel, employmentType, jobTitle, salary,
                    salaryCurrency, salaryInUsd, employeeResidence, remoteRatio,
                    companyLocation, companySize);
        } catch (NumberFormatException e) {
            // Obsługa błędów podczas parsowania rekordu CSV
            e.printStackTrace();
            return null;
        }
    }
}
