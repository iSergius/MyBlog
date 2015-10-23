package name.isergius.learn.myblog.ui;

import name.isergius.learn.myblog.domain.Article;
import name.isergius.learn.myblog.domain.Marker;
import name.isergius.learn.myblog.domain.Note;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kondratyev Sergey on 22.10.15.
 */
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = (Note) request.getServletContext().getAttribute("note");
        List<Article> articles = note.getAllPublishedArticles();
        List<Marker> markers = note.getAllPublishedMarkers();
        request.setAttribute("articles", articles);
        request.setAttribute("markers",markers);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
