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
 * CityInfo Servlet: corresponds with Servlet-3 in specification.
 */
@WebServlet("/cityinfo")
public class CityInfoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.jsp
        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);

        try {
            String orig = request.getParameter("from");
            String dest = request.getParameter("to");

            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            props.put("frsDatabase", "new://Resource?type=DataSource");
            props.put("frsDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
            props.put("frsDatabase.JdbcUrl", "jdbc:hsqldb:mem:frs_db");
            Context context = new InitialContext(props);

            CityInfo cityinfo = (CityInfo)context.lookup("java:global/frs/CityInfo");

            if (request.getParameter("query").equals("altitude")) {
                // Altitude
                // Get altitudes
                List<Double> altitudes = cityinfo.getAltitudes(orig);

                // Print the altitude
                if (altitudes.size() == 0) {
                    out.println("<br/><b>City name not found.</b>");
                } else {
                    out.println("<br/><b>Altitude of " + orig);
                    out.println("is: " + altitudes.get(0));
                    out.println("feet.<br/>");
                }
            }  else if (request.getParameter("query").equals("geography")) {
                // Geolocation
                // Get latitudes & longitudes
                List<Double> latitudes = cityinfo.getLatitudes(orig);
                List<Double> longitudes = cityinfo.getLongitudes(orig);

                // Print the geolocation
                if (latitudes.size() == 0 || longitudes.size() == 0) {
                    out.println("<br/><b>City name not found.</b>");
                } else {
                    out.println("<br/><b>Geolocation of " + orig);
                    out.println("is: (" + latitudes.get(0) + ", " + longitudes.get(0) + ")");
                    out.println("<br/>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
