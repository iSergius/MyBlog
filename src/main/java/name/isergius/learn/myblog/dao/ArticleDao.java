package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Article;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public interface ArticleDao extends Dao<Article> {

    Article readBy(long id, boolean published);

    List<Article> readAll(boolean published);
}
