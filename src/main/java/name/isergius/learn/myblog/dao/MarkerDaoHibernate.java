package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Marker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class MarkerDaoHibernate {

    private SessionFactory sessionFactory;

    public MarkerDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Marker readBy(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        Marker result = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            result = session.get(Marker.class, id);
            if (result == null) throw new DaoException("Entity with "+id+"Not found");

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    public Marker create(Marker marker) throws DaoException {
        if (marker.getId() != null) throw new DaoException("Entity have id: "+ marker.getId());

        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(marker);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return marker;
    }
}
