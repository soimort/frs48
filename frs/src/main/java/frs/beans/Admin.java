package frs.beans;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 0.1
 * @since  2014-01-10
 *
 * Admin EJB: corresponds with EJB-4 in specification.
 */
@Stateful
public class Admin {

    @PersistenceContext(unitName = "airline-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airlineEntityManager;

    @PersistenceContext(unitName = "airport-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager airportEntityManager;

    @PersistenceContext(unitName = "route-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager routeEntityManager;

    // Clean and update all tables.
    public void updateData() {
        cleanAirline();
        cleanAirport();
        cleanRoute();

        try {
            updateAirline();
            updateAirport();
            updateRoute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clean the Airline table.
    private void cleanAirline() {
        List<Airline> list = airlineEntityManager.createQuery("SELECT a from Airline as a").getResultList();
        for (Airline airline : list)
            airlineEntityManager.remove(airline);
    }

    // Clean the Airport table.
    private void cleanAirport() {
        List<Airport> list = airportEntityManager.createQuery("SELECT a from Airport as a").getResultList();
        for (Airport airport : list)
            airportEntityManager.remove(airport);
    }

    // Clean the Route table.
    private void cleanRoute() {
        List<Route> list = routeEntityManager.createQuery("SELECT r from Route as r").getResultList();
        for (Route route : list)
            routeEntityManager.remove(route);
    }

    // Update the Airline table.
    private void updateAirline() throws IOException {
        URL airlineUrl = new URL("http://sourceforge.net/p/openflights/code/HEAD/tree/openflights/data/airlines.dat?format=raw");
        BufferedReader airlineIn = new BufferedReader(new InputStreamReader(airlineUrl.openStream()));
        String airlineLine;
        while ((airlineLine = airlineIn.readLine()) != null) {
            String []s = parseCSV(airlineLine);
            String airlineCode = s[4].replaceAll("\"", "");
            String airlineName = s[1].replaceAll("\"", "");
            Airline airline = new Airline(airlineCode, airlineName);
            airlineEntityManager.persist(airline);
        }
        airlineIn.close();
    }

    // Update the Airport table.
    private void updateAirport() throws IOException {
        URL airportUrl = new URL("http://sourceforge.net/p/openflights/code/HEAD/tree/openflights/data/airports.dat?format=raw");
        BufferedReader airportIn = new BufferedReader(new InputStreamReader(airportUrl.openStream()));
        String airportLine;
        while ((airportLine = airportIn.readLine()) != null) {
            String []s = parseCSV(airportLine);
            String airportCode = s[4].replaceAll("\"", "");
            String airportName = s[1].replaceAll("\"", "");
            String city = s[2].replaceAll("\"", "");
            double latitude = Double.parseDouble(s[6]);
            double longitude = Double.parseDouble(s[7]);
            double altitude = Double.parseDouble(s[8]);
            Airport airport = new Airport(airportCode, airportName, city, latitude, longitude, altitude);
            airportEntityManager.persist(airport);
        }
        airportIn.close();
    }

    // Update the Route table.
    private void updateRoute() throws IOException {
        URL routeUrl = new URL("http://sourceforge.net/p/openflights/code/HEAD/tree/openflights/data/routes.dat?format=raw");
        BufferedReader routeIn = new BufferedReader(new InputStreamReader(routeUrl.openStream()));
        String routeLine;
        while ((routeLine = routeIn.readLine()) != null) {
            String []s = parseCSV(routeLine);
            String fromCode = s[2].replaceAll("\"", "");
            String toCode = s[4].replaceAll("\"", "");
            String airlineCode = s[0].replaceAll("\"", "");
            String flightNo = ""; // N/A
            double price = 0; // N/A
            Route route = new Route(fromCode, toCode, airlineCode, flightNo, price);
            routeEntityManager.persist(route);
        }
        routeIn.close();
    }

    // Parse a CSV line into a String array.
    private String[] parseCSV(String s) {
        ArrayList<String> r = new ArrayList<String>();
        int start = 0, pstart = 0, end = 0;
        while (end != -1) {
            end = s.indexOf(',', start);
            if (start < s.length() && s.charAt(start) == '"' && end - 1 >= 0 && s.charAt(end - 1) != '"') {
                start = end + 1;
                continue;
            }

            if (end != -1)
                r.add(s.substring(pstart, end));
            else
                r.add(s.substring(pstart));
            pstart = start = end + 1;
        }
        return r.toArray(new String[r.size()]);
    }
}
