package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class ArticleDaoHibernate extends AbstractDaoHibernate<Article> implements ArticleDao {

    public ArticleDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Article.class);
    }

    @Override
    public Article readBy(long id, boolean published) {
        Session session = getSessionFactory().openSession();
        Article result = (Article) session.createQuery("from Article a where a.published = :published and a.id = :id")
                                    .setBoolean("published", published)
                                    .setLong("id",id)
                                    .uniqueResult();
        session.close();
        return result;
    }

    @Override
    public Portion<Article> readAll(boolean published) {
        Map<String,Object> properties = new HashMap<>();
        String selectQuery = "from Article a where a.published = :published";
        String countQuery = "select count (a) from Article a where a.published = :published";
        properties.put("published", published);
        return new PortionHibernate<>(getSessionFactory(),selectQuery,countQuery,properties);
    }

    @Override
    public Portion<Article> readByMarker(long markerId, boolean published) {
        Map<String,Object> properties = new HashMap<>();
        String selectQuery = "select distinct article from Article article join article.markers as marker where article.published = :published and marker.id = :id";
        String countQuery = "select distinct count (article) from Article article join article.markers as marker where article.published = :published and marker.id = :id";
        properties.put("published", published);
        properties.put("id", markerId);
        return new PortionHibernate<>(getSessionFactory(),selectQuery,countQuery,properties);
    }
}
