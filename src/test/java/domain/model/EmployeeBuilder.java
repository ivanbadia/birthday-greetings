package domain.model;

import java.time.LocalDate;

/**
 * Created by Ivan on 09/07/2017.
 */
public class EmployeeBuilder {

    private String firstName = "John";
    private String lastName = "Doe";
    private LocalDate dateOfBirth = LocalDate.of(1980, 9, 10);
    private String email = "john.doe@foobar.com";

    public static Employee anEmployee(){
        return new EmployeeBuilder().build();
    }


    public Employee build(){
        return new Employee(firstName, lastName, dateOfBirth, email);
    }

    public EmployeeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
}
