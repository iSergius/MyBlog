package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class Article extends Model {

    private String title;
    private List<Marker> markers;
    private Boolean published;

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

    public List<Marker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Marker> markers) {
        this.markers = markers;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
