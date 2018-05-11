package domain.model.greetings;

import domain.model.employee.Employee;

/**
 * Created by Ivan on 09/07/2017.
 */
public interface GreetingsSender {
    void sendGreetingsTo(Employee employee);
}
