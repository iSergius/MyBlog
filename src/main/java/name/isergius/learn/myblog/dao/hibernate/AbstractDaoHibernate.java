package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.Dao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.Model;
import name.isergius.learn.myblog.dao.Portion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;

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
        Session session = sessionFactory.getCurrentSession();
        T result = session.get(clazz, id);
        if (result == null) throw new DaoException("Entity with "+id+"Not found");
        return result;
    }

    @Override
    public T create(T entity) throws DaoException {
        if (entity.getId() != null) throw new DaoException("Entity have id: "+entity.getId());
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void update(T entity) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        if (session.get(entity.getClass(),entity.getId()) == null) throw new DaoException("Update is not contain entity");
        session.clear();
        session.update(entity);
    }

    @Override
    public void deleteBy(long id) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        T entity = session.get(clazz, id);
        if (entity == null) throw new DaoException("Delete not contain entity");
        session.delete(entity);
    }

    @Override
    public Portion<T> read() {
        String selectQuery = "from " + clazz.getName();
        String countQuery = "select count (e) from "+clazz.getName()+" as e";
        return new PortionHibernate<>(sessionFactory,selectQuery, countQuery ,new HashMap<>());
    }

    protected SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
