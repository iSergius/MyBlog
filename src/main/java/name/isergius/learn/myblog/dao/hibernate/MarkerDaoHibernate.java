package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.domain.Marker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class MarkerDaoHibernate extends AbstractDaoHibernate<Marker> implements MarkerDao {

    public MarkerDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Marker.class);
    }

    @Override
    public List<Marker> readAll(boolean published) {
        Session session = null;
        Transaction transaction = null;
        List<Marker> result = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            result = (List<Marker>) session.createQuery("select marker from Marker as marker join marker.articles as article where article.published = :pub")
                    .setBoolean("pub", published)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
