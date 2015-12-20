package name.isergius.learn.myblog.dao;

import name.isergius.learn.myblog.domain.ConfigurationService;
import name.isergius.learn.myblog.domain.FileMetadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Kondratyev Sergey on 08.12.15.
 */
public class FileRepositoryLocal implements FileRepository {

    public static final String REPOSITORY_PROPERTY = "name.isergius.learn.myblog.dao.FileRepositoryLocal.String";
    private Path repositoryRoot;
    private ConfigurationService configurationService;

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void initRepository() throws IOException {
        String repositoryRootLocation = configurationService.getProperty(REPOSITORY_PROPERTY, String.class);
        this.repositoryRoot = Paths.get(repositoryRootLocation).normalize();
        if (!Files.exists(repositoryRoot))
            Files.createDirectory(repositoryRoot);
        else if (!Files.isReadable(repositoryRoot) || !Files.isWritable(repositoryRoot))
            throw new IOException("Repository Root path: " + repositoryRoot + " is not readable or writable");
    }

    @Override
    public void save(FileMetadata fileMetadata, byte[] file) {
        Path path = repositoryRoot.resolve(fileMetadata.getName());
        try {
            Files.write(path,file, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FileMetadata fileMetadata, byte[] file) {
        Path path = repositoryRoot.resolve(fileMetadata.getName());
        try {
            Files.write(path, file, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rename(FileMetadata oldMetadata, FileMetadata fileMetadata) {
        Path pathSource = repositoryRoot.resolve(oldMetadata.getName());
        Path pathTarget = repositoryRoot.resolve(fileMetadata.getName());
        try {
            Path path = Files.move(pathSource, pathTarget);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(FileMetadata fileMetadata) {
        Path path = repositoryRoot.resolve(fileMetadata.getName());
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] load(FileMetadata fileMetadata) {
        byte[] result = new byte[0];
        try {
            Path path = repositoryRoot.resolve(fileMetadata.getName());
            result = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
