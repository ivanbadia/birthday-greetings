package infrastructure.email;

import domain.model.greetings.email.Email;

import java.util.logging.Logger;

/**
 * Created by Ivan on 09/07/2017.
 */
public class EmailSender {
    private static final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public void send(Email email) {
        logger.info("Email "+email.toString()+" sent");
    }
}
