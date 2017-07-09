package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ivan on 09/07/2017.
 */
public class FileEmployeeRepositoryIT {

    @Test
    public void should_return_employees_born_on_a_given_date(){
        //Given
        String file = getClass().getClassLoader().getResource("employees.csv").getFile();
        FileEmployeeRepository repository = new FileEmployeeRepository(file);

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
}
