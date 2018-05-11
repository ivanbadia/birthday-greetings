package domain.model.greetings;

import domain.model.employee.Employee;
import domain.model.employee.EmployeeRepository;
import domain.model.greetings.BirthdayGreeter;
import domain.model.greetings.GreetingsSender;
import infrastructure.time.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Collections;

import static domain.model.EmployeeBuilder.anEmployee;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterShould {

    private static final int TODAY_MONTH = 7;
    private static final int TODAY_DAY_OF_MONTH = 9;
    private static final LocalDate TODAY = LocalDate.of(2017, TODAY_MONTH, TODAY_DAY_OF_MONTH);

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private GreetingsSender greetingsSender;

    @Mock
    private Clock clock;

    private BirthdayGreeter birthdayGreeter;

    @Before
    public void setUp() {
        birthdayGreeter = new BirthdayGreeter(employeeRepository, greetingsSender, clock);
    }

    @Test
    public void send_a_greetings_message_to_the_employees_whose_birthday_is_today(){
        given(clock.today()).willReturn(TODAY);
        Employee employee = anEmployee().build();
        given(employeeRepository.findEmployeesBornOn(MonthDay.of(TODAY_MONTH, TODAY_DAY_OF_MONTH)))
                .willReturn(Collections.singletonList(employee));

        birthdayGreeter.sendGreetings();

        verify(greetingsSender).sendGreetingsTo(employee);
    }
}
