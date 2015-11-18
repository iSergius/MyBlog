package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.ui.Page;

import java.time.LocalDate;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class NoteServiceImpl implements NoteService {

    private ArticleDao articleDao;
    private MarkerDao markerDao;

    public NoteServiceImpl(ArticleDao articleDao, MarkerDao markerDao) {
        this.articleDao = articleDao;
        this.markerDao = markerDao;
    }

    @Override
    public Page<Article> getArticles(Long size) {
        Portion<Article> portion = articleDao.read();
        return new Page<>(portion,size);
    }

    @Override
    public Page<Marker> getMarkers(Long size) {
        Portion<Marker> portion = markerDao.read();
        return new Page<>(portion,size);
    }

    @Override
    public Article getArticleBy(long id) {
        Article result = null;
        try {
            result = articleDao.readBy(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(Article article) {

        if (article.getPublished()) article.setPublishedDate(LocalDate.now());

        if (article.getId() == null) {
            try {
                articleDao.create(article);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } else {
            try {
                articleDao.update(article);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }
}
