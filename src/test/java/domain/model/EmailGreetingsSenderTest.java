package domain.model;

import infrastructure.email.Email;
import infrastructure.email.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailGreetingsSenderTest {

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private EmailGreetingsSender emailGreetingsSender;

    @Test
    public void greetings_email_should_be_sent() throws Exception {
        //Given
        String email = "john.doe@foobar.com";
        Employee employee = new Employee("John", "Doe", LocalDate.of(2016, 1, 1), email);

        //When
        emailGreetingsSender.sendGreetingsTo(employee);

        //Then
        verify(emailSender).send(new Email(email, "Happy birthday!", "Happy birthday, dear John!"));
    }

}