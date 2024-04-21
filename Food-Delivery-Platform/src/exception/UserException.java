package exception;

public class UserException extends RuntimeException{

    public UserException() { }

    public UserException(ExceptionType exceptionType, String message) {
        this.type = exceptionType;
        this.message = message;
    }

    public UserException(ExceptionType exceptionType) {
        this.type = exceptionType;
    }

    public ExceptionType getType() {
        return type;
    }

    public void setType(ExceptionType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UserException{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private ExceptionType type;
    private String message;
}
