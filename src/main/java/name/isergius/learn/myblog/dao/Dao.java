package name.isergius.learn.myblog.dao;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public interface Dao<T extends Model> {

    T readBy(long id) throws DaoException;

    T create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void deleteBy(long id) throws DaoException;

    Portion<T> read();

}
