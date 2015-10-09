package name.isergius.learn.myblog.dao;

import java.io.Serializable;

/**
 * Created by Kondratyev Sergey on 09.10.15.
 */
public abstract class Model implements Serializable {

    private Long id;

    public Model() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
