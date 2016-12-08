package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */
public class Article extends Model {

    private String title;
    private String content;
    private List<Marker> markers;
    private Boolean published;
    private Date publishedDate;
    private String disclaimer;

    public Article() {
        super();
        new Article("");
    }

    public Article(String title) {
        super();
        this.title = title;
        this.content = "";
        this.markers = new ArrayList<>();
        this.published = false;
        this.publishedDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
}
