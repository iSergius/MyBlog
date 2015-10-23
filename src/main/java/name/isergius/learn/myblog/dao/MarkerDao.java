package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Marker;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public interface MarkerDao extends Dao<Marker> {

    public List<Marker> readAll(boolean published);

    Marker readBy(long id, boolean published);

}
