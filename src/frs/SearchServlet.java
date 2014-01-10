package frs;

import frs.beans.Search;

import java.util.List; //
import frs.beans.*; //

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        this.getServletContext().getRequestDispatcher("/response.jsp").include(request, response);
        out.println("<h1>" + request.getParameter("query") + "</h1>");
        out.println("<br/><b>You are from: " + request.getParameter("from"));
        out.println(", to: " + request.getParameter("to") + "</b>");

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
            Context ctx = new InitialContext(props);

            Search search = (Search)ctx.lookup("SearchBeanRemote");

            String result = search.getData();
            out.println("<br/><b>" + result + "</b>");

            final Properties p = new Properties();
            p.put("airportDatabase", "new://Resource?type=DataSource");
            p.put("airportDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
            p.put("airportDatabase.JdbcUrl", "jdbc:hsqldb:mem:airport_db");
            final Context context = new InitialContext(p);
            Airports airports = (Airports)context.lookup("java:global/frs/Airports");
            airports.addAirport(new Airport("GKA", "Goroka", "Goroka", -6.081689, 145.391881, 5282));
            List<Airport> list = airports.getAirports();
            out.println("<br/><h2>" + list.size() + "</h2>");
            //out.println("<br/><h2>" + list.get(0).getCity() + "</h2>");
            //for (Airport airport : list)
            //    airports.deleteAirport(airport);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
