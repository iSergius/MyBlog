package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class Article extends Model {

    private String title;

    public Article() {
        super();
    }

    public Article(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
