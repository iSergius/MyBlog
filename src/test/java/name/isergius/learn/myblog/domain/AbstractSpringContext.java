package name.isergius.learn.myblog.domain;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/webmvc-config.xml",
        "classpath:spring/spring-config.xml",
        "classpath:test-spring-config.xml",
        "classpath:spring/security-config.xml",
        "classpath:spring/aop-config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public abstract class AbstractSpringContext extends Assert {
}
