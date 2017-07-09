package domain.model;

import java.time.MonthDay;
import java.util.List;

/**
 * Created by Ivan on 09/07/2017.
 */
public interface EmployeeRepository {
    List<Employee> findEmployeesBornOn(MonthDay monthDay);
}
