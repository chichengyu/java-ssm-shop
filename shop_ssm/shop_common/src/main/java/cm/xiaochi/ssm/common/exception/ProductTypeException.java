package cm.xiaochi.ssm.common.exception;

public class ProductTypeException extends Exception {
    public ProductTypeException() {
        super();
    }

    public ProductTypeException(String message) {
        super(message);
    }

    public ProductTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeException(Throwable cause) {
        super(cause);
    }
}
