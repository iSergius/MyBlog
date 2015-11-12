package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.UserDao;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        name.isergius.learn.myblog.domain.User user;
        try {
            user = userDao.readBy(username);
        } catch (DaoException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,user.getAuthorities());
    }

}
