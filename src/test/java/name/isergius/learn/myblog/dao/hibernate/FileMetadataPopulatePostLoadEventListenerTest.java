package name.isergius.learn.myblog.dao.hibernate;

import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * Created by Kondratyev Sergey on 11.12.15.
 */
@Ignore
@ContextConfiguration(loader = GenericXmlContextLoader.class,
        locations = {"classpath:spring/webmvc-config.xml",
                "classpath:spring/spring-config.xml",
                "classpath:test-spring-config.xml",
                "classpath:spring/security-config.xml",
                "classpath:spring/aop-config.xml"})
public class FileMetadataPopulatePostLoadEventListenerTest extends AbstractJUnit4SpringContextTests {




}
