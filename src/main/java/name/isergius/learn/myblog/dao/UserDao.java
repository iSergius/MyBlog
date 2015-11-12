package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.User;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public interface UserDao extends Dao<User> {

    public User readBy(String username) throws DaoException;

}
