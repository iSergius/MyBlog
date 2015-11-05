package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.Model;
import name.isergius.learn.myblog.dao.Portion;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 26.10.15.
 */
public class PortionHibernate<T extends Model> implements Portion<T> {

    private Session session;
    private Query selectQuery;
    private Query countQuery;
    private List<T> result;
    private Long count;

    public PortionHibernate(Session session, Query select, Query count) {
        this.session = session;
        this.selectQuery = select;
        this.countQuery = count;
    }

    @Override
    public List<T> result(Long index, Long size) {
        if (result == null) {
            if (size == 0) size = count();
                result = (List<T>) selectQuery
                        .setFirstResult(index.intValue())
                        .setMaxResults(size.intValue())
                        .list();
            if (session != null) {
                    session.close();
            }
        }
        return result;
    }

    @Override
    public Long count() {
        if (count == null) count = (Long) countQuery.uniqueResult();
        return count;
    }
}
