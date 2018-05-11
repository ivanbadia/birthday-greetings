package infrastructure.persistence;

import domain.model.employee.Employee;
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

    private static final Employee JOHN_DOE = new Employee("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com");
    private static final Employee PEDRO_GARCIA = new Employee("Pedro", "Garcia", LocalDate.of(1980, 10, 8), "pedro.garcia@foobar.com");

    @Test
    public void should_return_employees_born_on_a_given_date(){
        FileEmployeeRepository repository = new FileEmployeeRepository(EmployeesFile.path());
        MonthDay date = MonthDay.of(10, 8);

        List<Employee> employees = repository.findEmployeesBornOn(date);

        assertThat(employees)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactly(
                        JOHN_DOE,
                        PEDRO_GARCIA
                    );
    }

    @Test
    public void should_fail_if_file_is_not_valid() {
        FileEmployeeRepository repository = new FileEmployeeRepository(EmployeesFile.pathOfInvalidFile());

        Throwable throwable = catchThrowable(() -> repository.findEmployeesBornOn(MonthDay.now()));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(RepositoryAccessException.class);

    }



}
