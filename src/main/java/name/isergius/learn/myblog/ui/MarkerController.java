package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Kondratyev Sergey on 23.10.15.
 */
public class MarkerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkRequest(request)) {
            response.sendError(404);
            return;
        }
        Long articleId = Long.valueOf(request.getPathInfo().substring(1));

        setModelAttributes(request,articleId);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        Marker marker = note.getPublishedMarkerBy(articleId);
        List<Article> articles = marker.getArticles();
        List<Marker> markers = note.getAllPublishedMarkers();
        request.setAttribute("title",marker.getTitle());
        request.setAttribute("articles",articles);
        request.setAttribute("markers",markers);
    }
}
