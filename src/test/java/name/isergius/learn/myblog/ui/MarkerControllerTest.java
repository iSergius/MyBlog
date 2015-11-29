package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Kondratyev Sergey on 29.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/webmvc-config.xml",
        "classpath:spring/spring-config.xml",
        "classpath:test-spring-config.xml",
        "classpath:spring/security-config.xml",
        "classpath:spring/aop-config.xml"})
public class MarkerControllerTest {

    @Autowired
    @InjectMocks
    private MarkerController markerController;

    @Mock
    private NoteService noteService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(markerController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/marker/edit")
                .param("id", "1")
                .param("title", "Mark"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/note"));

        Mockito.verify(noteService).save(Matchers.any(Marker.class));
    }
    @Test
    public void testNew() throws Exception {
        mockMvc.perform(post("/marker/edit")
                .param("title", "Mark"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/note"));

        Mockito.verify(noteService).save(Matchers.any(Marker.class));
    }
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post("/marker/1/delete"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/note"));

        Mockito.verify(noteService).deleteMarker(1);
    }

}
