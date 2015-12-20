package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.Setting;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 21.12.15.
 */
public interface SettingDao extends Dao<Setting> {

    Setting readBy(String name) throws DaoException;

    void save(List<Setting> settings) throws DaoException;
}
