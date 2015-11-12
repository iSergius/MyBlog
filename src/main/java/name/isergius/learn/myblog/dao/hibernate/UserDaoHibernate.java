package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.UserDao;
import name.isergius.learn.myblog.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class UserDaoHibernate extends AbstractDaoHibernate<User> implements UserDao {

    public UserDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public User readBy(String username) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        User result = (User) session.createQuery("SELECT user FROM User user WHERE user.username = :username")
                .setString("username",username)
                .uniqueResult();
        if (result == null) throw new DaoException("User is not contain with "+username+" username");
        return result;
    }
}
