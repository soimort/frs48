package frs;

import frs.beans.Search;

import javax.ejb.embeddable.EJBContainer; //
import java.util.List; //
import frs.beans.*; //

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);
        out.println("<h1>" + request.getParameter("query") + "</h1>");
        out.println("<br/><b>You are from: " + request.getParameter("from"));
        out.println(", to: " + request.getParameter("to") + "</b>");

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            Context ctx = new InitialContext(props);

            Search search = (Search)ctx.lookup("SearchBeanRemote");

            String result = search.getData();
            out.println("<br/><b>" + result + "</b>");

            final Properties p = new Properties();
            p.put("movieDatabase", "new://Resource?type=DataSource");
            p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
            p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
            final Context context = new InitialContext(p);
            Movies movies = (Movies) context.lookup("java:global/frs/Movies");
            movies.addMovie(new Movie("Beat Takeshi", "Outrage", 2010));
            movies.addMovie(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
            movies.addMovie(new Movie("Joel Coen", "Fargo", 1996));
            movies.addMovie(new Movie("Joel Coen", "The Big Lebowski", 1998));
            List<Movie> list = movies.getMovies();
            out.println("<br/><h2>" + list.get(0).getDirector() + "</h2>");
            for (Movie movie : list)
                movies.deleteMovie(movie);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
