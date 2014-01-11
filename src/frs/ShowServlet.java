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
 * Show Servlet.
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.jsp
        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);

        if (request.getParameter("query").equals("show")) {
            // Show inbound/outbound flights
            try {
                String airportCode = request.getParameter("airportCode");
                String select = request.getParameter("select");

                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
                props.put("frsDatabase", "new://Resource?type=DataSource");
                props.put("frsDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
                props.put("frsDatabase.JdbcUrl", "jdbc:hsqldb:mem:frs_db");
                Context context = new InitialContext(props);

                Search search = (Search)context.lookup("java:global/frs/Search");

                if (select.equals("inbound")) {
                    // Get all inbound routes
                    List<Route> routes = search.getInboundRoutes(airportCode);

                    // Print inbound routes
                    if (routes.size() == 0) {
                        out.println("<br/><b>No inbound flights found.");
                    } else {
                        out.println("<br/><b>Inbound flights of " + airportCode + ":</b><br/>");
                        for (Route route : routes) {
                            out.println("[" + route.getAirlineCode() + "] :");
                            out.println(route.getFromCode() + " -> " + route.getToCode());
                            out.println("<br/>");
                        }
                    }
                } else if (select.equals("outbound")) {
                    // Get all outbound routes
                    List<Route> routes = search.getOutboundRoutes(airportCode);

                    //Print outbound routes
                    if (routes.size() == 0) {
                        out.println("<br/><b>No outbound flights found.");
                    } else {
                        out.println("<br/><b>Outbound flights of " + airportCode + ":</b><br/>");
                        for (Route route : routes) {
                            out.println("[" + route.getAirlineCode() + "] :");
                            out.println(route.getFromCode() + " -> " + route.getToCode());
                            out.println("<br/>");
                        }
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
