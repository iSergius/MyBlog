package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.domain.Article;
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
    public Article readPublishedBy(long id, boolean published) {
        Session session = null;
        Transaction transaction = null;
        Article result = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            result = (Article) session.createQuery("from Article a where a.published = :published")
                                    .setBoolean("published", published)
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