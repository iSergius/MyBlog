package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.Dao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.Model;
import name.isergius.learn.myblog.dao.Portion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public abstract class AbstractDaoHibernate<T extends Model> implements Dao<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory;

    public AbstractDaoHibernate(SessionFactory sessionFactory, Class<T> clazz) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T readBy(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        T result = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            result = session.get(clazz, id);
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

    @Override
    public T create(T entity) throws DaoException {
        if (entity.getId() != null) throw new DaoException("Entity have id: "+entity.getId());

        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(entity);

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
        return entity;
    }

    @Override
    public void update(T entity) throws DaoException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(entity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Update not contain entity");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteBy(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            T entity = session.get(clazz, id);
            if (entity == null) throw new DaoException("Delete not contain entity");
            session.delete(entity);

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
    }

    @Override
    public Portion<T> read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query selectQuery = session.createQuery("from " + clazz.getName());
        Query countQuery = session.createQuery("select count (e) from "+clazz.getName()+" as e");

        return new PortionHibernate<>(session,selectQuery,countQuery);
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
