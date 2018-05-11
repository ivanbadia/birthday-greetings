package domain.model.greetings;

import domain.model.employee.EmployeeRepository;
import domain.model.greetings.GreetingsSender;
import infrastructure.time.Clock;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * Created by Ivan on 09/07/2017.
 */
public class BirthdayGreeter {
    private final EmployeeRepository employeeRepository;
    private final GreetingsSender greetingsSender;
    private final Clock clock;

    public BirthdayGreeter(EmployeeRepository employeeRepository, GreetingsSender greetingsSender, Clock clock) {
        this.employeeRepository = employeeRepository;
        this.greetingsSender = greetingsSender;
        this.clock = clock;
    }

    public void sendGreetings() {
        LocalDate today = clock.today();
        employeeRepository.findEmployeesBornOn(MonthDay.of(today.getMonth(), today.getDayOfMonth()))
                .forEach(employee -> greetingsSender.sendGreetingsTo(employee));
    }
}
