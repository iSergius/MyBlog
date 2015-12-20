package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.ui.Page;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 17.11.15.
 */
public interface BlogService {

    String BLOG_TITLE = "name.isergius.learn.myblog.domain.BlogService.blogTitle.String";

    String getTitle();

    Marker retrieveMarkerBy(long id);

    List<Marker> retrieveAllMarkers();

    Article retrieveArticleBy(long id);

    Page<Article> retrieveArticles(long size);

    Page<Article> retrieveArticlesHasMarkerBy(long id, long size);

}
