package infrastructure.persistence;

import domain.model.employee.Employee;
import domain.model.employee.EmployeeRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileEmployeeRepository implements EmployeeRepository{
    private static final DateTimeFormatter BIRTH_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final int FIRST_NAME_POSITION = 1;
    private static final int LAST_NAME_POSITION = 0;
    private static final int BIRTH_DATE_POSITION = 2;
    private static final int EMAIL_POSITION = 3;

    private final String file;

    private Function<String, Employee> parseEmployee  = line -> {
        List<String> employeeValues = Arrays.stream(line.split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList());
        return new Employee(employeeValues.get(FIRST_NAME_POSITION), employeeValues.get(LAST_NAME_POSITION), LocalDate.parse(employeeValues.get(BIRTH_DATE_POSITION), BIRTH_DATE_FORMAT), employeeValues.get(EMAIL_POSITION));
    };

    public FileEmployeeRepository(String file) {
        this.file = file;
    }

    @Override
    public List<Employee> findEmployeesBornOn(MonthDay monthDay) {
        try (Stream<String> stream = Files.lines(Paths.get(file)).skip(1)) {
            return stream.map(parseEmployee)
                    .filter(employee -> employee.birthdayIsOn(monthDay))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RepositoryAccessException("Error reading the repository file", e);
        }
    }
}
