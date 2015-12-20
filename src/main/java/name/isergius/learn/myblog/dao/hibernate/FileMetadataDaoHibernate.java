package name.isergius.learn.myblog.dao.hibernate;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.FileMetadataDao;
import name.isergius.learn.myblog.domain.FileMetadata;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Kondratyev Sergey on 08.12.15.
 */
public class FileMetadataDaoHibernate extends AbstractDaoHibernate<FileMetadata> implements FileMetadataDao {

    public FileMetadataDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, FileMetadata.class);
    }

    @Override
    public FileMetadata readBy(String title) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        FileMetadata result;
        result = (FileMetadata) session.createQuery("from FileMetadata f where f.name like :title")
                .setString("title", title)
                .uniqueResult();
        if (result == null) throw new DaoException("file with name: "+title+" is not contain");
        return result;
    }
}
