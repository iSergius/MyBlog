package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Note;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Kondratyev Sergey on 16.10.15.
 */
public class ArticleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || !Pattern.matches("/\\d+", pathInfo)) {
            response.sendError(404);
            return;
        }
        Integer articleId = Integer.valueOf(pathInfo.substring(1));
        ServletContext servletContext = request.getServletContext();
        Note note = (Note) servletContext.getAttribute("note");
        note.getPublishedArticleBy(articleId);

        request.getRequestDispatcher("article.jsp").forward(request,response);
    }

}
