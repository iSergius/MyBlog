package name.isergius.learn.myblog.dao;

/**
 * Created by Kondratyev Sergey on 10.10.15.
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
