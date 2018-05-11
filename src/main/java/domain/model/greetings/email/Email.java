package domain.model.greetings.email;

import java.util.Objects;

/**
 * Created by Ivan on 09/07/2017.
 */
public class Email {
    private final String to;
    private final String subject;
    private final String message;

    public Email(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(to, email.to) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(message, email.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, subject, message);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Email{");
        sb.append("to='").append(to).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
