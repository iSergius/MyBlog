package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.FileMetadata;

/**
 * Created by Kondratyev Sergey on 12.12.15.
 */
public interface FileRepository {
    void save(FileMetadata fileMetadata, byte[] file);

    void update(FileMetadata fileMetadata, byte[] file);

    void delete(FileMetadata fileMetadata);

    byte[] load(FileMetadata fileMetadata);

    void rename(FileMetadata oldMetadata, FileMetadata fileMetadata);
}
