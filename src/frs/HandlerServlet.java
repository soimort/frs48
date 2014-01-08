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

        if (request.getParameter("search") != null) {
            this.getServletContext().getRequestDispatcher("/search").include(request, response);
        }
    }
}
