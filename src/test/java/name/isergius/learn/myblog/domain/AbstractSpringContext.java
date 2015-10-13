package name.isergius.learn.myblog.domain;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Created by Kondratyev Sergey on 13.10.15.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({"spring/spring-config.xml","test-spring-config.xml"})
public abstract class AbstractSpringContext extends Assert {
}
