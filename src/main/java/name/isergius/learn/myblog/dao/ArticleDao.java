package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public interface ArticleDao extends Dao<Article> {

    Article readBy(long id, boolean published);

    Portion<Article> readAll(boolean published);

    Portion<Article> readByMarker(long markerId, boolean published);
}
