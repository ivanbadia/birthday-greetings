package infrastructure.persistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Created by Ivan on 09/07/2017.
 */
public class EmployeesFile {

    private static final String VALID_EMPLOYEES_FILE = "employees.csv";
    private static final String INVALID_EMPLOYEES_FILE = "invalid_employees.csv";


    public static String path() {
        return pathOf(VALID_EMPLOYEES_FILE);
    }

    public static String pathOfInvalidFile() {
        return pathOf(INVALID_EMPLOYEES_FILE);
    }

    private static String pathOf(String fileName) {
        try {
            URI uri = EmployeesFile.class.getClassLoader().getResource(fileName).toURI();
            return Paths.get(uri).toString();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Test file "+fileName+" cannot be read", e);
        }
    }

}
