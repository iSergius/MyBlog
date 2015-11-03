package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.dao.Dao;
import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.domain.Marker;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * Created by Kondratyev Sergey on 01.11.15.
 */
public class MarkerCollectionFormBinder<T extends Dao> extends CustomCollectionEditor
{
    private final T dao;

    public MarkerCollectionFormBinder(final T daoIn, final Class collectionType)
    {
        super(collectionType, true);
        dao = daoIn;
    }

    @Override
    protected Object convertElement(final Object element)
    {
        Object result = null;
        if (element.getClass() == Marker.class) {
            return (Marker) element;
        }
        try
        {
            // forms should return the id as the itemValue
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