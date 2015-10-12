package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.domain.Article;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class ArticleDaoHibernate extends AbstractDaoHibernate<Article> implements ArticleDao {

    public ArticleDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Article.class);
    }

}
