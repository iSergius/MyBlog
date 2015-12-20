package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.FileMetadataDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.ui.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * Created by Kondratyev Sergey on 08.12.15.
 */
@ContextConfiguration(loader = GenericXmlContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class FileMetadataServiceTest extends AbstractJUnit4SpringContextTests {

    @InjectMocks
    @Autowired
    private FileService fileService;
    @Mock
    private FileMetadataDao fileMetadataDao;
    @Mock
    private Portion<FileMetadata> portion;
    @Mock
    private FileMetadata fileMetadata;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFiles() throws Exception {
        Mockito.when(fileMetadataDao.read()).thenReturn(portion);

        Page<FileMetadata> page = fileService.getFilesMetadata(10L);

        Assert.assertNotNull(page);
    }

    @Test
    public void testGetFileByName() throws Exception {
        final String FILE_NAME = "picture.jpg";
        Mockito.when(fileMetadataDao.readBy(FILE_NAME)).thenReturn(this.fileMetadata);
        Mockito.when(this.fileMetadata.getName()).thenReturn(FILE_NAME);

        FileMetadata fileMetadata = fileService.getFileMetadataBy(FILE_NAME);

        Assert.assertNotNull(fileMetadata);
        Assert.assertEquals(FILE_NAME, fileMetadata.getName());
    }
}
