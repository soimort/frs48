package frs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

import frs.beans.Hello;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);
        out.println("<h1>" + request.getParameter("query") + "</h1>");
        out.println("<br/><b>You are from: " + request.getParameter("from"));
        out.println(", to: " + request.getParameter("to") + "</b>");

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            Context ctx = new InitialContext(props);
            Object ref = ctx.lookup("HelloBeanRemote");
            Hello h = (Hello)PortableRemoteObject.narrow(ref, Hello.class);
            String result = h.sayHello();
            out.println(result);
        } catch (Exception e) {
            out.println(e);
        }
    }
}
