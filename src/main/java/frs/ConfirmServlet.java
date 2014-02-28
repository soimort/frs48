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
 * Confirm Servlet.
 */
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Call response.html
        this.getServletContext().getRequestDispatcher("/response.html").include(request, response);

        if (request.getParameter("confirm").equals("confirm")) {
            // Confirm
            try {
                String id = request.getParameter("route");
                out.println(id);
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
