package name.isergius.learn.myblog.dao;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Kondratyev Sergey on 09.10.15.
 */
public abstract class Model implements Serializable {

    private Long id;

    public Model() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
