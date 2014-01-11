package frs;

import frs.beans.*;

import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-11
 *
 * Search Servlet.
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.jsp
        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);

        if (request.getParameter("query").equals("search")) {
            // Search
            try {
                String orig = request.getParameter("from");
                String dest = request.getParameter("to");

                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
                props.put("frsDatabase", "new://Resource?type=DataSource");
                props.put("frsDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
                props.put("frsDatabase.JdbcUrl", "jdbc:hsqldb:mem:frs_db");
                Context context = new InitialContext(props);

                Search search = (Search)context.lookup("java:global/frs/Search");

                // Search for all possible routes
                List<Route> routes = search.getRoutes(orig, dest);

                // Print all routes
                if (routes.size() == 0) {
                    out.println("<br/><b>Travel from " + orig);
                    out.println("to " + dest);
                    out.println("is not available.</b>");
                } else {
                    out.println("<br/><b>Travel from " + orig);
                    out.println("to " + dest);
                    out.println("is available:</b>");
                    out.println("<br/>");
                    for (Route route : routes) {
                        out.println("[" + route.getAirlineCode() + "] :");
                        out.println(route.getFromCode() + " -> " + route.getToCode());
                        out.println("<br/>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
