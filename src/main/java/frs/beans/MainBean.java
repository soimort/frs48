package frs.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;

import java.io.StringWriter;
import java.io.PrintWriter;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

class GMailAuthenticator extends Authenticator {
    String username;
    String password;

    public GMailAuthenticator(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}

@SessionScoped
@Named
public class MainBean implements Serializable {

    private String travelFrom;
    private String travelTo;
    private String airportCode;
    private String inOutBound;
    private Route selectedRoute;
    private boolean hasCarRental;
    private CarRental carRental;

    private List<Route> routes;

    private String message;

    //@Inject
    //private AirlineDAO airlineDAO;

    @Inject
    private AirportDAO airportDAO;

    @Inject
    private RouteDAO routeDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private BankDAO bankDAO;

    @Inject
    private TravellerDAO travellerDAO;

    //@Inject
    //private CarRental carRentalDAO;

    public MainBean() {
        this.carRental = new CarRental();
        this.message = "";
    }

    // Search routes between two cities.
    public String search() {
        try {
            List<Airport> origAirports = airportDAO.getAllByCity(travelFrom);
            List<Airport> destAirports = airportDAO.getAllByCity(travelTo);
            this.routes = routeDAO.getAllByAirports(origAirports, destAirports);
            this.message = "All flights from " + travelFrom + " to " + travelTo + ":";
        } catch (Exception e) {
            this.message = "An error occured.";
        }
        return "search.xhtml?faces-redirect=true";
    }

    // Show distance between two cities.
    public String distance() {
        try {
            this.message = "The distance from " + travelFrom +
                " to " + travelTo + " is " +
                airportDAO.calcDistance(travelFrom, travelTo) + " km.";
        } catch (Exception e) {
            this.message = "City name(s) not found.";
        }
        return "display.xhtml?faces-redirect=true";
    }

    // Show altutude of the city.
    public String altitude() {
        try {
            this.message = "The altitude of " + travelFrom + " is " +
                airportDAO.getFirstAltitude(travelFrom) + " feet.";
        } catch (Exception e) {
            this.message = "City name not found.";
        }
        return "display.xhtml?faces-redirect=true";
    }

    // Show geolocation of the city.
    public String geography() {
        try {
            this.message = "The geolocation of " + travelFrom + " is (" +
                airportDAO.getFirstLatitude(travelFrom) + ", " +
                airportDAO.getFirstLongitude(travelFrom) + ").";
        } catch (Exception e) {
            this.message = "City name not found.";
        }
        return "display.xhtml?faces-redirect=true";
    }

    // Display all inbound/outbound flights of the airport.
    public String display() {
        try {
            if (inOutBound.equals("inbound"))
                this.routes = routeDAO.getInboundRoutes(airportCode);
            else
                this.routes = routeDAO.getOutboundRoutes(airportCode);
            this.message = "All " + inOutBound + " flights of " + airportCode + ":";
        } catch (Exception e) {
            this.message = "An error occured.";
        }
        return "display2.xhtml?faces-redirect=true";
    }

    // Book a flight.
    public String book(String idNo) {
        try {
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();

            Double price = this.selectedRoute.getPrice();
            Bank bank = bankDAO.getBankByIdNo(idNo);
            bank.setBalance(bank.getBalance() - price);
            bankDAO.update(bank);

            User user = userDAO.getUserByIdNo(idNo);
            String name = user.getName();
            String email = user.getEmail();
            Date date = new Date();
            String fromCode = this.selectedRoute.getFromCode();
            String toCode = this.selectedRoute.getToCode();
            Traveller traveller = new Traveller(idNo,
                                                name,
                                                email,
                                                date,
                                                fromCode,
                                                toCode,
                                                price);
            travellerDAO.save(traveller);

            this.message = "You have successfully booked the flight from " + travelFrom + " to " + travelTo + ". Congratulations!";

            // JavaMail
            /*
            String to = "mort.yao@gmail.com";
            String from = "mort.yao@gmail.com";
            String password = "****************";
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(properties, new GMailAuthenticator(from, password));

            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Confirmation");
            msg.setSentDate(new Date());

            // Set message content
            msg.setText(this.message);

            //Send the message
            Transport.send(msg);
            */

            transaction.commit();

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            this.message = sw.toString();
        }
        return "confirm.xhtml?faces-redirect=true";
    }

    // Car rental.
    public String rent(String idNo) {
        try {
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            transaction.begin();

            //Service rentalServiceService = Service.create(new URL("http://127.0.0.1:8080/frs/RentalService?wsdl"), new QName("http://frs.org/wsdl", "RentalServiceWebService"));
            //RentalService rentalService = rentalServiceService.getPort(RentalService.class);
            RentalService rentalService = new RentalService();
            double payment = rentalService.saveRental(this.carRental.getLicenseNo(),
                                     this.carRental.getDriverName(),
                                     this.carRental.getModelYear(),
                                     this.carRental.getKilometers(),
                                     this.carRental.getSeatsNo(),
                                     this.carRental.getDays());

            // Update Bank
            Bank bank = bankDAO.getBankByIdNo(idNo);
            bank.setBalance(bank.getBalance() + payment);
            bankDAO.update(bank);

            this.message = "You have successfully rented a car. Your payment: " + payment;
            this.hasCarRental = false;

            transaction.commit();

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            this.message = sw.toString();
        }
        return "confirm.xhtml?faces-redirect=true";
    }

    // Getter for travelFrom.
    public String getTravelFrom() {
        return travelFrom;
    }

    // Setter for travelFrom.
    public void setTravelFrom(String travelFrom) {
        this.travelFrom = travelFrom;
    }

    // Getter for travelTo.
    public String getTravelTo() {
        return travelTo;
    }

    // Setter for travelTo.
    public void setTravelTo(String travelTo) {
        this.travelTo = travelTo;
    }

    // Getter for airportCode.
    public String getAirportCode() {
        return airportCode;
    }

    // Setter for airportCode.
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    // Getter for inOutBound.
    public String getInOutBound() {
        return inOutBound;
    }

    // Setter for inOutBound.
    public void setInOutBound(String inOutBound) {
        this.inOutBound = inOutBound;
    }

    // Getter for selectedRoute.
    public Route getSelectedRoute() {
        return selectedRoute;
    }

    // Setter for selectedRoute.
    public void setSelectedRoute(Route selectedRoute) {
        this.selectedRoute = selectedRoute;
    }

    // Setter for selectedRoute.
    public void setSelectedRoute(ValueChangeEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        this.selectedRoute = context.getApplication().evaluateExpressionGet(context, "#{route}", Route.class);
    }

    // Getter for hasCarRental.
    public boolean getHasCarRental() {
        return hasCarRental;
    }

    // Setter for hasCarRental.
    public void setHasCarRental(boolean hasCarRental) {
        this.hasCarRental = hasCarRental;
    }

    // Getter for carRental.
    public CarRental getCarRental() {
        return carRental;
    }

    // Setter for carRental.
    public void setCarRental(CarRental carRental) {
        this.carRental = carRental;
    }

    // Getter for routes.
    public List<Route> getRoutes() {
        return routes;
    }

    // Setter for routes
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    // Getter for message.
    public String getMessage() {
        return message;
    }

    // Setter for message.
    public void setMessage(String message) {
        this.message = message;
    }

}
