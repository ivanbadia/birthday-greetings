package infrastructure.persistence;

import domain.model.Employee;
import domain.model.EmployeeRepository;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 09/07/2017.
 */
public class FileEmployeeRepository implements EmployeeRepository{
    public FileEmployeeRepository(String file) {

    }

    @Override
    public List<Employee> findEmployeesBornOn(MonthDay monthDay) {
        return new ArrayList<>();
    }
}
