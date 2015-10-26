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

/**
 * Created by Kondratyev Sergey on 25.10.15.
 */
public class NoteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setModelAttributes(request);
        request.getRequestDispatcher("/note.jsp").forward(request,response);
    }

    private void setModelAttributes(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Note note = (Note) servletContext.getAttribute("note");
        Page<Article> articles = note.getArticles(10L);
        Page<Marker> markers = note.getMarkers(10L);
        request.setAttribute("articles",articles.result(0L));
        request.setAttribute("markers",markers.result(0L));
    }
}
