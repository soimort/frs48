package frs.beans;
import javax.ejb.Stateless;

@Stateless
public class SearchBean implements Search {
    private String data = "Good";

    public String getData() {
        return data;
    }
}
