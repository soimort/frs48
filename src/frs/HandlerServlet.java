package frs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/handler")
public class HandlerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        if (request.getParameter("query").equals("search")) {
            this.getServletContext().getRequestDispatcher("/search").include(request, response);
        } else if (request.getParameter("query").equals("distance")) {
            // call servlet 2
            out.println(request.getParameter("query"));
        } else if (request.getParameter("query").equals("altitude")) {
            // call servlet 3
            out.println(request.getParameter("query"));
        } else if (request.getParameter("query").equals("geography")) {
            // call servlet 3
            out.println(request.getParameter("query"));
        }
    }
}
