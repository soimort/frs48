package frs.beans;
import javax.ejb.Remote;

@Remote
public interface Hello {
    public String sayHello();
}
