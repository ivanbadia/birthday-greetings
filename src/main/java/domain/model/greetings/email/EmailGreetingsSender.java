package domain.model.greetings.email;

import domain.model.employee.Employee;
import domain.model.greetings.GreetingsSender;
import infrastructure.email.EmailSender;

/**
 * Created by Ivan on 09/07/2017.
 */
public class EmailGreetingsSender implements GreetingsSender {

    private EmailSender emailSender;

    public EmailGreetingsSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendGreetingsTo(Employee employee) {
        emailSender.send(greetingsEmailFor(employee));
    }

    private Email greetingsEmailFor(Employee employee) {
        String body = String.format("Happy birthday, dear %s!", employee.getFirstName());
        return new Email(employee.getEmail(), "Happy birthday!", body);
    }
}
