package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.Model;
import name.isergius.learn.myblog.dao.Portion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PortionHibernate<T extends Model> implements Portion<T> {

    private Query selectQuery;
    private Query countQuery;
    private List<T> result;
    private Long count;

    public PortionHibernate(SessionFactory sessionFactory, String query, String count, Map<String,Object> properties) {
        Session session = sessionFactory.openSession();
        this.selectQuery = session.createQuery(query);
        selectQuery.setProperties(properties);
        this.countQuery = session.createQuery(count);
        countQuery.setProperties(properties);
    }

    @Override
    public List<T> result(Long index, Long size) {
        if (result == null) {
            if (size == 0) size = count();
                result = (List<T>) selectQuery
                        .setFirstResult(index.intValue())
                        .setMaxResults(size.intValue())
                        .list();
        }
        return result;
    }

    @Override
    public Long count() {
        if (count == null) count = (Long) countQuery.uniqueResult();
        return count;
    }
}
