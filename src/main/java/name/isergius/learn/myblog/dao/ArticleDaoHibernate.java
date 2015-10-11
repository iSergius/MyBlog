package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class ArticleDaoHibernate {

    private SessionFactory sessionFactory;

    public ArticleDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Article readBy(long id) throws DaoException {
        Session session = sessionFactory.openSession();
        Article result = session.get(Article.class, id);
        if (result == null) throw new DaoException();
        session.close();
        return result;
    }

    public Article create(Article article) throws DaoException {
        if (article.getId() != null) throw new DaoException();
        Session session = sessionFactory.openSession();
        session.save(article);
        session.close();
        return article;
    }

    public void update(Article article) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(article);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteBy(long id) throws DaoException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Article article = session.get(Article.class, id);
        if (article == null) throw new DaoException();
        session.delete(article);
        session.getTransaction().commit();
        session.close();
    }
}
