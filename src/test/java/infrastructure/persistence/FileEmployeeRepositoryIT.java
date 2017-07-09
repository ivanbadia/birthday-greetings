package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeBuilder;
import domain.model.RepositoryAccessException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ivan on 09/07/2017.
 */
public class FileEmployeeRepositoryIT {

    @Test
    public void should_return_employees_born_on_a_given_date() throws URISyntaxException {
        //Given
        FileEmployeeRepository repository = new FileEmployeeRepository(validEmployeesFile());

        //When
        List<Employee> employees = repository.findEmployeesBornOn(MonthDay.of(10, 8));

        //Then
        assertThat(employees)
                .hasSize(2)
                .containsExactly(
                    EmployeeBuilder.builder().withLastName("Doe").withFirstName("John").withDateOfBirth(LocalDate.of(1982, 10, 8)).withEmail("john.doe@foobar.com").build(),
                    EmployeeBuilder.builder().withLastName("Garcia").withFirstName("Pedro").withDateOfBirth(LocalDate.of(1980, 10, 8)).withEmail("pedro.garcia@foobar.com").build()
                    );

    }

    @Test
    public void should_fail_if_file_is_not_valid() throws URISyntaxException {
        //Given
        FileEmployeeRepository repository = new FileEmployeeRepository(invalidEmployeesFile());

        //When
        Throwable throwable = Assertions.catchThrowable(() -> repository.findEmployeesBornOn(MonthDay.now()));

        //Then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(RepositoryAccessException.class);

    }

    private String validEmployeesFile() throws URISyntaxException {
        String fileName = "employees.csv";
        URI uri = getClass().getClassLoader().getResource(fileName).toURI();
        return Paths.get(uri).toString();
    }

    private String invalidEmployeesFile() throws URISyntaxException {
        String fileName = "invalid_employees.csv";
        URI uri = getClass().getClassLoader().getResource(fileName).toURI();
        return Paths.get(uri).toString();
    }
}
