package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ivan on 09/07/2017.
 */
public class FileEmployeeRepository implements EmployeeRepository{
    private static final DateTimeFormatter BIRTH_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private final String file;

    public FileEmployeeRepository(String file) {
        this.file = file;
    }

    @Override
    public List<Employee> findEmployeesBornOn(MonthDay monthDay) {
        try (Stream<String> stream = Files.lines(Paths.get(file)).skip(1)) {
            return stream.map(line -> {
                List<String> values = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
                return new Employee(values.get(1), values.get(0),
                        LocalDate.parse(values.get(2), BIRTH_DATE_FORMAT), values.get(3));
            }).filter(employee -> employee.birthdayIsOn(monthDay))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
