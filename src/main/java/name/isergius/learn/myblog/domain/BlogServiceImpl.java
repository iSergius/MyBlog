package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.ui.Page;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class BlogServiceImpl implements BlogService {

    private String title;
    private NoteService note;
    private MarkerDao markerDao;
    private ArticleDao articleDao;

    public BlogServiceImpl() {}

    public BlogServiceImpl(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MarkerDao getMarkerDao() {
        return markerDao;
    }

    public void setMarkerDao(MarkerDao markerDao) {
        this.markerDao = markerDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Marker retrieveMarkerBy(long id) {
        return markerDao.readBy(id,true);
    }

    @Override
    public List<Marker> retrieveAllMarkers() {
        return markerDao.readAll(true).result(0L,0L);
    }

    @Override
    public Article retrieveArticleBy(long id) {
        return articleDao.readBy(id,true);
    }

    @Override
    public Page<Article> retrieveArticles(long size) {
        return new Page<>(articleDao.readAll(true),size);
    }

    @Override
    public Page<Article> retrieveArticlesHasMarkerBy(long id, long size) {
        return new Page<>(articleDao.readByMarker(id,true),size);
    }
}
