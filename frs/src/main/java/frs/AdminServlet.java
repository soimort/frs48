package frs;

import frs.beans.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
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
 * Admin Servlet: handles administration requests.
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.html
        this.getServletContext().getRequestDispatcher("/response.html").include(request, response);

        if (request.getParameter("operation").equals("update")) {
            // Update
            try {
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
                props.put("frsDatabase", "new://Resource?type=DataSource");
                props.put("frsDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
                props.put("frsDatabase.JdbcUrl", "jdbc:hsqldb:mem:frs_db");
                Context context = new InitialContext(props);

                // Clean and update all data
                Admin admin = (Admin)context.lookup("java:global/frs/Admin");
                admin.updateData();
                out.println("<h2>Update Successful</h2><br/>");

                // Print Airline stats
                Airlines airlines = (Airlines)context.lookup("java:global/frs/Airlines");
                List<Airline> airlineList = airlines.getAirlines();
                out.println(airlineList.size() + " airlines added.<br/>");

                // Print Airport stats
                Airports airports = (Airports)context.lookup("java:global/frs/Airports");
                List<Airport> airportList = airports.getAirports();
                out.println(airportList.size() + " airports added.<br/>");

                // Print Route stats
                Routes routes = (Routes)context.lookup("java:global/frs/Routes");
                List<Route> routeList = routes.getRoutes();
                out.println(routeList.size() + " routes added.<br/>");

            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
