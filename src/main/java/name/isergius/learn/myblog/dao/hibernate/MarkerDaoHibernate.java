package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.domain.Marker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kondratyev Sergey on 12.10.15.
 */
public class MarkerDaoHibernate extends AbstractDaoHibernate<Marker> implements MarkerDao {

    public MarkerDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory,Marker.class);
    }

    @Override
    public Portion<Marker> readAll(boolean published) {
        Map<String,Object> properties = new HashMap<>();
        String selectQuery = "select distinct marker from Marker as marker join marker.articles as article where article.published = :pub";
        String countQuery = "select count (distinct marker) from Marker as marker join marker.articles as article where article.published = :pub";
        properties.put("pub", published);
        return new PortionHibernate<Marker>(getSessionFactory(),selectQuery,countQuery,properties);
    }

    @Override
    public Marker readBy(long id, boolean published) {
        Session session = getSessionFactory().getCurrentSession();
        Marker result = (Marker) session.createQuery("select marker from Marker as marker join marker.articles as article where article.published = :pub and marker.id = :id")
                    .setBoolean("pub", published)
                    .setLong("id",id)
                    .uniqueResult();
        return result;
    }
}
