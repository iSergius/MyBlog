package name.isergius.learn.myblog;

import name.isergius.learn.myblog.domain.Blog;
import name.isergius.learn.myblog.domain.Note;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Kondratyev Sergey on 15.10.15.
 */

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        Blog blog = applicationContext.getBean(Blog.class);
        Note note = applicationContext.getBean(Note.class);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("blog", blog);
        servletContext.setAttribute("note", note);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
