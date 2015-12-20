package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.ui.Page;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
public interface FileService {

    Page<FileMetadata> getFilesMetadata(Long size);

    FileMetadata getFileMetadataBy(String title);

    FileMetadata getFileMetadataBy(Long id);

    void saveFile(FileMetadata fileMetadata, byte[] file);

    byte[] getFileBy(FileMetadata fileMetadata);

    void deleteBy(Long id);

    void saveFileMetadata(FileMetadata fileMetadata);
}
