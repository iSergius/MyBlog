package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.ui.Page;
import name.isergius.learn.myblog.dao.Portion;

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

    public List<Marker> getAllPublishedMarkers() {
        return markerDao.readAll(true);
    }

    public List<Article> getAllPublishedArticles() {
        return articleDao.readAll(true);
    }

    public Marker getPublishedMarkerBy(long id) {
        return markerDao.readBy(id,true);
    }

    public Page<Article> getArticles(Long size) {
        Portion<Article> portion = articleDao.read();
        return new Page<>(portion,size);
    }

    public Page<Marker> getMarkers(Long size) {
        Portion<Marker> portion = markerDao.read();
        return new Page<>(portion,size);
    }
}
