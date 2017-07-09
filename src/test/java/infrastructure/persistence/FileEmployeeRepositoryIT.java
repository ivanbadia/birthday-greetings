package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeBuilder;
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
        FileEmployeeRepository repository = new FileEmployeeRepository(employeesFile());

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

    private String employeesFile() throws URISyntaxException {
        URI uri = getClass().getClassLoader().getResource("employees.csv").toURI();
        return Paths.get(uri).toString();
    }
}
