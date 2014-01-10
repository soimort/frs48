package frs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-10
 *
 * Handler Servlet: handles all user requests and forwards them to corresponding Servlets.
 */
@WebServlet("/handler")
public class HandlerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (request.getParameter("query").equals("search")) {
            // Call SearchServlet (Servlet-1)
            this.getServletContext().getRequestDispatcher("/search").include(request, response);

        } else if (request.getParameter("query").equals("distance")) {
            // Call DistanceServlet (Servlet-2)
            this.getServletContext().getRequestDispatcher("/distance").include(request, response);

        } else if (request.getParameter("query").equals("altitude")) {
            // Call CityInfoServlet (Servlet-3)
            this.getServletContext().getRequestDispatcher("/cityinfo").include(request, response);

        } else if (request.getParameter("query").equals("geography")) {
            // Call CityInfoServlet (Servlet-3)
            this.getServletContext().getRequestDispatcher("/cityinfo").include(request, response);

        }
    }
}
