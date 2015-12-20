package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.Dao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.domain.Setting;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * Created by Kondratyev Sergey on 27.12.15.
 */
public class SettingCollectionFormBinder<T extends Dao> extends CustomCollectionEditor {
    private final T dao;

    public SettingCollectionFormBinder(final T daoIn, final Class collectionType) {
        super(collectionType, true);
        dao = daoIn;
    }

    @Override
    protected Object convertElement(final Object element) {
        Object result = null;
        if (element.getClass() == Setting.class) {
            return (Setting) element;
        }
        try {
            Integer id = Integer.valueOf(element.toString());
            try {
                result = dao.readBy(id);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
