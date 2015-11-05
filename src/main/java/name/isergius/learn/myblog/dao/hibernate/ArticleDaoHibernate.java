package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.domain.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class ArticleDaoHibernate extends AbstractDaoHibernate<Article> implements ArticleDao {

    public ArticleDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Article.class);
    }

    @Override
    public Article readBy(long id, boolean published) {
        Session session = null;
        Transaction transaction = null;
        Article result = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            result = (Article) session.createQuery("from Article a where a.published = :published and a.id = :id")
                                    .setBoolean("published", published)
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

    @Override
    public Portion<Article> readAll(boolean published) {
        Session session = getSessionFactory().openSession();
        Query selectQuery = session.createQuery("from Article a where a.published = :published")
                    .setBoolean("published", published);
        Query countQuery = session.createQuery("select count (a) from Article a where a.published = :published")
                .setBoolean("published", published);
        return new PortionHibernate<>(session,selectQuery,countQuery);
    }
}
