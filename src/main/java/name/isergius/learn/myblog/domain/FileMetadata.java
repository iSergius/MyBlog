package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
public class FileMetadata extends Model {

    private String name;
    private String mimeType;

    public FileMetadata() {
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

}
