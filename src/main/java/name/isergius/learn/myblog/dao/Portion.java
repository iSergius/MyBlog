package name.isergius.learn.myblog.dao;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
public interface Portion<T extends Model> {

    public List<T> result(Long index, Long size);

    public Long count();

}
