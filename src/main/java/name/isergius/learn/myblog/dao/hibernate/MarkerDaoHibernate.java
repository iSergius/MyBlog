package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.domain.Marker;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class MarkerDaoHibernate extends AbstractDaoHibernate<Marker> implements MarkerDao {

    public MarkerDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Marker.class);
    }

    @Override
    public Portion<Marker> readAll(boolean published) {
        Session session = getSessionFactory().openSession();
        Query selectQuery = session.createQuery("select distinct marker from Marker as marker join marker.articles as article where article.published = :pub")
                .setBoolean("pub", published);
        Query countQuery = session.createQuery("select count (distinct marker) from Marker as marker join marker.articles as article where article.published = :pub")
                .setBoolean("pub", published);
        return new PortionHibernate<Marker>(session,selectQuery,countQuery);
    }

    @Override
    public Marker readBy(long id, boolean published) {
        Session session = null;
        Transaction transaction = null;
        Marker result = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            result = (Marker) session.createQuery("select marker from Marker as marker join marker.articles as article where article.published = :pub and marker.id = :id")
                    .setBoolean("pub", published)
                    .setLong("id",id)
                    .uniqueResult();

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
