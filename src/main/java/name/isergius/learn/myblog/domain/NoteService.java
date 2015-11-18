package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.ui.Page;

/**
 * Created by Kondratyev Sergey on 18.11.15.
 */
public interface NoteService {
    Page<Article> getArticles(Long size);

    Page<Marker> getMarkers(Long size);

    Article getArticleBy(long id);

    void save(Article article);
}
