package domain.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Ivan on 09/07/2017.
 */
public class Employee {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String email;

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
