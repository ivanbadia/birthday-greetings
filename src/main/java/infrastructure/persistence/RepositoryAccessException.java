package infrastructure.persistence;

public class RepositoryAccessException extends RuntimeException {
    public RepositoryAccessException(String message, Exception cause) {
        super(message, cause);
    }
}
