package name.isergius.learn.myblog.domain;

/**
 * Created by Kondratyev Sergey on 13.11.15.
 */
public class UserServiceException extends Exception {

    public UserServiceException() {
        super();
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
