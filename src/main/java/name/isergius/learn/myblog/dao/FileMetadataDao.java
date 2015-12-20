package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.FileMetadata;

/**
 * Created by Kondratyev Sergey on 08.12.15.
 */
public interface FileMetadataDao extends Dao<FileMetadata> {

    FileMetadata readBy(String title) throws DaoException;

}
