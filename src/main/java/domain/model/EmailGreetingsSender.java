package domain.model;

import infrastructure.email.Email;
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
        return new Email(employee.getEmail(), "Happy birthday!", String.format("Happy birthday, dear %s!", employee.getFirstName()));
    }
}
