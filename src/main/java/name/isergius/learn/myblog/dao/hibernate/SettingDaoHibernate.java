package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.SettingDao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.domain.Setting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 23.12.15.
 */
public class SettingDaoHibernate extends AbstractDaoHibernate<Setting> implements SettingDao {

    public SettingDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, Setting.class);
    }

    public Setting readBy(String name) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        Setting result = (Setting) session.createQuery("SELECT s FROM Setting s WHERE s.name = :name")
                                    .setString("name", name)
                                    .uniqueResult();
        if (result == null) throw new DaoException("Result for name: "+name+" is not found");
        return result;
    }

    @Override
    public void save(List<Setting> settings) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        for (Setting setting : settings) {
            session.saveOrUpdate(setting);
        }
    }

}
