package application;

import domain.model.Employee;
import domain.model.EmployeeRepository;
import domain.model.GreetingsSender;
import infrastructure.time.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private GreetingsSender greetingsSender;

    @Mock
    private Clock clock;

    @InjectMocks
    private BirthdayGreeter birthdayGreeter;

    @Test
    public void should_send_a_greetings_email_to_the_employees_whose_birthday_is_today(){
        //SetUp
        int month = 7;
        int dayOfMonth = 9;
        LocalDate today = LocalDate.of(2017, month, dayOfMonth);
        when(clock.today()).thenReturn(today);
        Employee employee = new Employee("John", "Doe", LocalDate.of(2016, month, dayOfMonth), "john.doe@foobar.com");
        when(employeeRepository.findEmployeesBornOn(MonthDay.of(month, dayOfMonth))).thenReturn(Collections.singletonList(employee));

        //When
        birthdayGreeter.sendGreetings();

        //Then
        verify(greetingsSender).sendGreetingsTo(employee);
    }
}
