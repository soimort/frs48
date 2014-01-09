package frs.beans;
import javax.ejb.Stateless;

@Stateless
public class HelloBean implements Hello {
    public String sayHello() {
        return "Hello, EJB";
    }
}
