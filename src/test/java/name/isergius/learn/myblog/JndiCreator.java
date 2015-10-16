package name.isergius.learn.myblog;

import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by Kondratyev Sergey on 15.10.15.
 */
public class JndiCreator {

    private DataSource dataSource;

    public void init() {
        try {
            SimpleNamingContextBuilder simpleNamingContextBuilder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            simpleNamingContextBuilder.bind("java:comp/env/jdbc/myblog",this.dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
