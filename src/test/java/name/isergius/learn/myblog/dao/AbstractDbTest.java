package name.isergius.learn.myblog.dao;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Created by Kondratyev Sergey on 05.10.15.
 */

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({"spring/spring-config.xml","test-spring-config.xml"})
public abstract class AbstractDbTest extends Assert {

}
