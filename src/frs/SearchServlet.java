package frs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        out.println("<B><BR>You are from: " + request.getParameter("from"));
        out.println(", to: " + request.getParameter("to") + "</B>");

        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);
    }
}
