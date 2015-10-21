package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
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

        if (checkRequest(request)) {
            response.sendError(404);
            return;
        }
        Long articleId = Long.valueOf(request.getPathInfo().substring(1));

        setModelAttributes(request,articleId);

        request.getRequestDispatcher("/article.jsp").forward(request, response);
    }

    private boolean checkRequest(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || !Pattern.matches("/\\d+", pathInfo)) {
            return true;
        } else {
            return false;
        }
    }

    private void setModelAttributes(HttpServletRequest request, Long articleId) {
        ServletContext servletContext = request.getServletContext();
        Note note = (Note) servletContext.getAttribute("note");
        Article article = note.getPublishedArticleBy(articleId);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+article+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        request.setAttribute("article",article);
    }

}
