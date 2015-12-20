package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.FileMetadata;
import name.isergius.learn.myblog.domain.FileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kondratyev Sergey on 20.12.15.
 */
public class FileControllerTest extends AbstractControllerTest {

    @Autowired
    @InjectMocks
    private FileController fileController;
    @Mock
    private FileService fileService;
    @Mock
    private Page<FileMetadata> fileMetadataPage;
    @Mock
    private FileMetadata fileMetadata;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void testShow() throws Exception {
        Mockito.when(fileService.getFilesMetadata(10L)).thenReturn(fileMetadataPage);

        mockMvc.perform(get("/file"))
                .andExpect(status().is(200));

        Mockito.verify(fileService).getFilesMetadata(10L);
    }

    @Test
    public void testDownload() throws Exception {
        Mockito.when(fileMetadata.getMimeType()).thenReturn("image/jpg");
        Mockito.when(fileService.getFileMetadataBy("picture.jpg")).thenReturn(fileMetadata);
        Mockito.when(fileService.getFileBy(fileMetadata)).thenReturn(new byte[0]);

        MockHttpServletResponse response = mockMvc.perform(get("/file/picture.jpg")).andReturn().getResponse();

        Assert.assertEquals("image/jpg",response.getContentType());
    }

    @Test
    public void testEdit() throws Exception {
        Mockito.when(fileService.getFileMetadataBy(1L)).thenReturn(fileMetadata);

        mockMvc.perform(get("/file/1/edit"))
                .andExpect(status().is(200));

        Mockito.verify(fileService).getFileMetadataBy(1L);
    }

    @Test
    public void testUpdate() throws Exception {


        mockMvc.perform(post("/file/1/edit").contentType("application/x-www-form-urlencoded").requestAttr("fileMetadata", fileMetadata))
                .andExpect(status().is(302));

        Mockito.verify(fileService).saveFileMetadata(Matchers.any(FileMetadata.class));
    }

    @Test
    public void testSave() throws Exception {
        MockMultipartFile file = new MockMultipartFile("fileData", "picture.jpg", "image/jpg", "some jpg".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/edit").file(file))
                        .andExpect(status().is(302));

        Mockito.verify(fileService).saveFile(Matchers.any(FileMetadata.class), Matchers.any(byte[].class));
    }

    @Test
    public void testUpdateFile() throws Exception {
        Mockito.when(fileService.getFileMetadataBy(1L)).thenReturn(fileMetadata);
        MockMultipartFile file = new MockMultipartFile("fileData", "picture.jpg", "image/jpg", "some jpg".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/1/edit").file(file))
                .andExpect(status().is(302));

        Mockito.verify(fileService).saveFile(Matchers.any(FileMetadata.class), Matchers.any(byte[].class));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post("/file/1/delete"));

        Mockito.verify(fileService).deleteBy(1L);
    }
}
