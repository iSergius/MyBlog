package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class Marker extends Model {

    private String title;

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
}
