package domain.model.greetings.email;

import domain.model.EmployeeBuilder;
import domain.model.employee.Employee;
import domain.model.greetings.email.Email;
import domain.model.greetings.email.EmailGreetingsSender;
import infrastructure.email.EmailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailGreetingsSenderShould {

    @Mock
    private EmailSender emailSender;

    private EmailGreetingsSender emailGreetingsSender;

    @Before
    public void setUp() {
        emailGreetingsSender = new EmailGreetingsSender(emailSender);
    }

    @Test
    public void should_send_greeting_email_to_employee(){
        String email = "john.doe@foobar.com";
        String name = "John";
        Employee employee = new EmployeeBuilder()
                .withFirstName(name)
                .withEmail(email)
                .build();

        emailGreetingsSender.sendGreetingsTo(employee);

        verify(emailSender).send(new Email(email, "Happy birthday!", "Happy birthday, dear "+name+"!"));
    }

}