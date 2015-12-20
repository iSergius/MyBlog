package name.isergius.learn.myblog.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Kondratyev Sergey on 14.01.16.
 */
public class SettingTest {

    private static final String PROPERTY = "property.Integer";
    private static final String TYPE = "Integer";
    private static final String VALUE = "1";
    private Setting setting;

    @Before
    public void setUp() throws Exception {
        setting = new Setting();
        setting.setName(PROPERTY);
        setting.setType(TYPE);
        setting.setValue(VALUE);
    }

    @Test
    public void testIsSet() throws Exception {
        Assert.assertTrue(setting.isSet());
    }

    @Test
    public void testIsSetNull() throws Exception {
        setting.setValue(null);
        Assert.assertFalse(setting.isSet());
    }

    @Test
    public void testIsSetEmpty() throws Exception {
        setting.setValue("");
        Assert.assertFalse(setting.isSet());
    }

}
