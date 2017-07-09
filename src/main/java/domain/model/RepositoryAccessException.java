package domain.model;

public class RepositoryAccessException extends RuntimeException {
    public RepositoryAccessException(String message, Exception cause) {
        super(message, cause);
    }
}
