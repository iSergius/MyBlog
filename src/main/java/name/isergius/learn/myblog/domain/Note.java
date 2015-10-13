package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class Note {

    private ArticleDao articleDao;

    public Note(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public Article getPublishedArticleBy(long id) {
        return articleDao.readPublishedBy(id,true);
    }

}
