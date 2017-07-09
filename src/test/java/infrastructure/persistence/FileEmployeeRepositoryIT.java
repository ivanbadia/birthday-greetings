package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeBuilder;
import domain.model.RepositoryAccessException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * Created by Ivan on 09/07/2017.
 */
public class FileEmployeeRepositoryIT {

    @Test
    public void should_return_employees_born_on_a_given_date(){
        //Given
        FileEmployeeRepository repository = new FileEmployeeRepository(EmployeesFile.path());
        MonthDay date = MonthDay.of(10, 8);

        //When
        List<Employee> employees = repository.findEmployeesBornOn(date);

        //Then
        assertThat(employees)
                .hasSize(2)
                .containsExactly(
                    new EmployeeBuilder().withLastName("Doe").withFirstName("John").withDateOfBirth(LocalDate.of(1982, 10, 8)).withEmail("john.doe@foobar.com").build(),
                    new EmployeeBuilder().withLastName("Garcia").withFirstName("Pedro").withDateOfBirth(LocalDate.of(1980, 10, 8)).withEmail("pedro.garcia@foobar.com").build()
                    );

    }

    @Test
    public void should_fail_if_file_is_not_valid() {
        //Given
        FileEmployeeRepository repository = new FileEmployeeRepository(EmployeesFile.pathOfInvalidFile());

        //When
        Throwable throwable = catchThrowable(() -> repository.findEmployeesBornOn(MonthDay.now()));

        //Then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(RepositoryAccessException.class);

    }



}
