package frs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cityinfo")
public class CityInfoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);
        out.println("<h1>" + request.getParameter("query") + "</h1>");
        out.println("<br/><b>You are from: " + request.getParameter("from"));
        out.println(", to: " + request.getParameter("to") + "</b>");
    }
}
