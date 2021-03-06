package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.ArticleDao;
import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.ui.Page;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class BlogServiceImpl implements BlogService {

    private static final long ALL = 0L;
    private MarkerDao markerDao;
    private ArticleDao articleDao;
    private ConfigurationService configurationService;

    public BlogServiceImpl() {}

    public String getTitle() {
        return configurationService.getProperty(BLOG_TITLE,String.class);
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void setMarkerDao(MarkerDao markerDao) {
        this.markerDao = markerDao;
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
        return markerDao.readAll(true).result(ALL,ALL);
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
