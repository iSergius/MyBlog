package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.AuthorityDao;
import name.isergius.learn.myblog.domain.Authority;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 12.11.15.
 */
public class AuthorityDaoHibernate extends AbstractDaoHibernate<Authority> implements AuthorityDao {

    public AuthorityDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Authority.class);
    }

}
