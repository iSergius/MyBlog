package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.MarkerDao;
import name.isergius.learn.myblog.dao.Portion;
import name.isergius.learn.myblog.ui.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by Kondratyev Sergey on 04.11.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogTest {

    @Mock
    private MarkerDao markerDao;
    @Mock
    private List<Marker> markers;
    @Mock
    private Portion<Marker> markerPortion;
    @Mock
    private Page<Marker> markerPage;
    @Mock
    private Marker marker;

    private Blog blog = null;

    @Before
    public void setUp() throws Exception {
        blog = new Blog();
        blog.setMarkerDao(markerDao);
    }

    @Test
    public void testGettingPublishedMarker() throws Exception {
        Mockito.when(markerDao.readBy(1L, true)).thenReturn(marker);

        Marker marker = blog.getMarkerBy(1L);

        Assert.assertNotNull(marker);
    }

    @Test
    public void testGetAllPublishedMarkers() throws Exception {
        Mockito.when(markerDao.readAll(true)).thenReturn(markerPortion);

        List<Marker> markers = blog.getAllMarkers();

        Assert.assertNotNull(markers);
    }
}
