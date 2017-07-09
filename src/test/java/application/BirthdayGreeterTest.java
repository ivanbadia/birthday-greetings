package application;

import domain.model.Employee;
import domain.model.EmployeeRepository;
import domain.model.GreetingsSender;
import infrastructure.time.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private GreetingsSender greetingsSender;

    @Mock
    private Clock clock;

    @Mock
    private BirthdayGreeter birthdayGreeter;

    @Test
    public void should_send_a_greetings_email_to_the_employees_whose_birthday_is_today(){
        //SetUp
        Mockito.when(clock.today()).thenReturn(LocalDate.of(2017, 7, 9));
        Employee employee = new Employee("John", "Doe", LocalDate.of(2016, 7, 9), "john.doe@foobar.com");
        Mockito.when(employeeRepository.findEmployeesBornOn(MonthDay.of(7, 9))).thenReturn(Collections.singletonList(employee));

        //When
        birthdayGreeter.sendGreetings();

        //Then
        Mockito.verify(greetingsSender).sendGreetingsTo(employee);
    }
}
