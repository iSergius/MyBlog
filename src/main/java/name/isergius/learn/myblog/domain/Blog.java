package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class Blog extends Model {

    private String title;
    private Note note;

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
}
