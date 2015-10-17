package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.DaoException;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class ArticleDaoFeick implements ArticleDao {

    @Override
    public Article readPublishedBy(long id, boolean published) {
        Article result = new Article("Feick Article");
        result.setPublished(published);
        result.setId(id);
        return result;
    }

    @Override
    public Article readBy(long id) throws DaoException {
        return null;
    }

    @Override
    public Article create(Article entity) throws DaoException {
        return null;
    }

    @Override
    public void update(Article entity) throws DaoException {

    }

    @Override
    public void deleteBy(long id) throws DaoException {

    }
}