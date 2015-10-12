package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.domain.Marker;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class MarkerDaoHibernate extends AbstractDaoHibernate<Marker> implements MarkerDao {

    public MarkerDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Marker.class);
    }

}
