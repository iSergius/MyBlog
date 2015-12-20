package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.FileMetadataDao;
import name.isergius.learn.myblog.dao.FileRepository;
import name.isergius.learn.myblog.ui.Page;

/**
 * Created by Kondratyev Sergey on 26.11.15.
 */
public class FileServiceImpl implements FileService {

    private FileMetadataDao fileMetadataDao;
    private FileRepository fileRepository;

    @Override
    public Page<FileMetadata> getFilesMetadata(Long size) {
        return new Page<>(fileMetadataDao.read(), size);
    }

    @Override
    public FileMetadata getFileMetadataBy(String title) {
        FileMetadata result = null;
        try {
            result = fileMetadataDao.readBy(title);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public FileMetadata getFileMetadataBy(Long id) {
        FileMetadata result = null;
        try {
            result = fileMetadataDao.readBy(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveFile(FileMetadata fileMetadata, byte[] file) {
        if (fileMetadata.isNew()) {
            try {
                FileMetadata metadata = fileMetadataDao.create(fileMetadata);
                fileRepository.save(metadata,file);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileRepository.save(fileMetadata,file);
                fileMetadataDao.update(fileMetadata);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFileMetadata(FileMetadata fileMetadata) {
        try {
            FileMetadata oldMetadata = fileMetadataDao.readBy(fileMetadata.getId());
            if (!oldMetadata.getName().equals(fileMetadata.getName())) {
                fileRepository.rename(oldMetadata, fileMetadata);
            }
            fileMetadataDao.update(fileMetadata);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getFileBy(FileMetadata fileMetadata) {
        return fileRepository.load(fileMetadata);
    }

    @Override
    public void deleteBy(Long id) {
        try {
            FileMetadata metadata = fileMetadataDao.readBy(id);
            fileMetadataDao.deleteBy(id);
            fileRepository.delete(metadata);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void setFileMetadataDao(FileMetadataDao fileMetadataDao) {
        this.fileMetadataDao = fileMetadataDao;
    }

    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
}
