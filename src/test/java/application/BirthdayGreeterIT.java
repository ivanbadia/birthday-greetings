package application;

import domain.model.EmailGreetingsSender;
import infrastructure.email.Email;
import infrastructure.email.EmailSender;
import infrastructure.persistence.EmployeesFile;
import infrastructure.persistence.FileEmployeeRepository;
import infrastructure.time.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BirthdayGreeterIT {

    @Mock
    private Clock clock;

    @Spy
    private EmailSender emailSender;

    private BirthdayGreeter birthdayGreeter;

    @Before
    public void setUp(){
        birthdayGreeter = new BirthdayGreeter(new FileEmployeeRepository(EmployeesFile.path()), new EmailGreetingsSender(emailSender), clock);
    }

    @Test
    public void should_send_a_greetings_email_to_the_employees_whose_birthday_is_today(){
        //SetUp
        when(clock.today()).thenReturn(LocalDate.of(1982, 10, 8));

        //When
        birthdayGreeter.sendGreetings();

        //Then
        verify(emailSender).send(new Email("john.doe@foobar.com", "Happy birthday!", "Happy birthday, dear John!"));
        verify(emailSender).send(new Email("pedro.garcia@foobar.com", "Happy birthday!", "Happy birthday, dear Pedro!"));
        verifyNoMoreInteractions(emailSender);
    }
}
