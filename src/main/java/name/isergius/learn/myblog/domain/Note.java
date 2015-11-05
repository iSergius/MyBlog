package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.ui.Page;
import name.isergius.learn.myblog.dao.Portion;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
public class Note {

    private ArticleDao articleDao;
    private MarkerDao markerDao;

    public Note(ArticleDao articleDao, MarkerDao markerDao) {
        this.articleDao = articleDao;
        this.markerDao = markerDao;
    }

    public Article getPublishedArticleBy(long id) {
        return articleDao.readBy(id, true);
    }

    /*@Deprecated
    public List<Marker> getAllPublishedMarkers() {
        return markerDao.readAll(true).result(0L,1000L);
    }*/

    public List<Article> getAllPublishedArticles() {
        return articleDao.readAll(true);
    }

    /*@Deprecated
    public Marker getPublishedMarkerBy(long id) {
        return markerDao.readBy(id,true);
    }*/

    public Page<Article> getArticles(Long size) {
        Portion<Article> portion = articleDao.read();
        return new Page<>(portion,size);
    }

    public Page<Marker> getMarkers(Long size) {
        Portion<Marker> portion = markerDao.read();
        return new Page<>(portion,size);
    }

    public Article getArticleBy(long id) {
        Article result = null;
        try {
            result = articleDao.readBy(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void save(Article article) {
        if (article.getPublished()) article.setPublishedDate(LocalDate.now());
        try {
            articleDao.update(article);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
