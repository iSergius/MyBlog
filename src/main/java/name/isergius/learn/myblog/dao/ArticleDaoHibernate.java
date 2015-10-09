package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class ArticleDaoHibernate {

    private SessionFactory sessionFactory;

    public ArticleDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Article readBy(long id) {
        Session session = sessionFactory.openSession();
        Article result = session.get(Article.class, id);
        session.close();
        return result;
    }

}
