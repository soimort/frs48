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
 * Distance Servlet: corresponds with Servlet-2 in specification.
 */
@WebServlet("/distance")
public class DistanceServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.jsp
        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);

        if (request.getParameter("query").equals("distance")) {
            // Distance
            try {
                String orig = request.getParameter("from");
                String dest = request.getParameter("to");

                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
                props.put("frsDatabase", "new://Resource?type=DataSource");
                props.put("frsDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
                props.put("frsDatabase.JdbcUrl", "jdbc:hsqldb:mem:frs_db");
                Context context = new InitialContext(props);

                Distance distance = (Distance)context.lookup("java:global/frs/Distance");

                // Calculate distance between two cities
                List<Double> distances = distance.calcDistance(orig, dest);

                // Print the distance
                if (distances.size() == 0) {
                    out.println("<br/><b>City name not found.</b>");
                } else {
                    out.println("<br/><b>Distance from " + orig);
                    out.println("to " + dest);
                    out.println("is: " + distances.get(0));
                    out.println("km.<br/>");
                }

            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
