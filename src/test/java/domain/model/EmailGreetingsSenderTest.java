package domain.model;

import infrastructure.email.Email;
import infrastructure.email.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailGreetingsSenderTest {

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private EmailGreetingsSender emailGreetingsSender;

    @Test
    public void should_send_greeting_email_to_employee(){
        //Given
        String email = "john.doe@foobar.com";
        Employee employee = new EmployeeBuilder().withEmail(email).build();

        //When
        emailGreetingsSender.sendGreetingsTo(employee);

        //Then
        verify(emailSender).send(new Email(email, "Happy birthday!", "Happy birthday, dear John!"));
    }

}