package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.DaoException;
import name.isergius.learn.myblog.dao.SettingDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kondratyev Sergey on 20.12.15.
 */
public class ConfigurationServiceTest extends AbstractSpringContext {

    private final String TEST_PROPERTY = "test.property.String";
    private final String PROPERTY_NAME = "property.Integer";

    @Autowired
    @InjectMocks
    private ConfigurationServiceImpl configurationService;
    @Mock
    private SettingDao settingDao;
    @Mock
    private Setting setting;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDefaultProperty() throws Exception {
        Mockito.when(settingDao.readBy(PROPERTY_NAME)).thenThrow(DaoException.class);

        Integer value = (Integer) configurationService.getProperty(PROPERTY_NAME,Integer.class);

        Assert.assertEquals(new Integer(1), value);
    }

    @Test
    public void testGetProperty() throws Exception {
        Mockito.when(setting.exportValue()).thenReturn(2);
        Mockito.when(setting.getValue()).thenReturn("2");
        Mockito.when(setting.getType()).thenReturn("Integer");
        Mockito.when(setting.isSet()).thenReturn(true);
        Mockito.when(settingDao.readBy(PROPERTY_NAME)).thenReturn(setting);

        Integer value = (Integer) configurationService.getProperty(PROPERTY_NAME,Integer.class);

        Assert.assertEquals(new Integer(2), value);
    }

    @Test
    public void testSetProperty() throws Exception {

        configurationService.setProperty(PROPERTY_NAME, 3);

        Mockito.verify(settingDao).update(Matchers.any(Setting.class));
    }

    @Test
    public void testLoadPropertiesWithEnvironmentVariables() throws Exception {
        Mockito.when(settingDao.readBy(TEST_PROPERTY)).thenThrow(DaoException.class);
        String expected = System.getenv("HOME");
        String actual = configurationService.getProperty(TEST_PROPERTY, String.class);
        Assert.assertEquals(expected,actual);
    }

}
