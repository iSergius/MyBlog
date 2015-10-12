package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class Marker extends Model {

    private String title;
    private List<Article> articles;

    public Marker() {
        super();
    }

    public Marker(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
