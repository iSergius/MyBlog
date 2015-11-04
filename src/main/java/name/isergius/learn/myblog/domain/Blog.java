package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class Blog extends Model {

    private String title;
    private Note note;
    private MarkerDao markerDao;

    public Blog() {}

    public Blog(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public MarkerDao getMarkerDao() {
        return markerDao;
    }

    public void setMarkerDao(MarkerDao markerDao) {
        this.markerDao = markerDao;
    }

    public Marker getMarkerBy(long id) {
        return markerDao.readBy(id,true);
    }
}
