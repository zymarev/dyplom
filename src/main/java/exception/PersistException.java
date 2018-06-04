package exception;

public class PersistException extends RuntimeException {

    public PersistException(String msg){
        super(msg);
    }

    public PersistException(Throwable throwable) {
        super(throwable);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
