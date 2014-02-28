package frs.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class UserBean implements Serializable {

    private User user;

    private String message;

    @Inject
    private UserDAO userDAO;

    public UserBean() {
        this.user = new User();
        this.message = "";
    }

    public String login() {
        User u;
        try {
            u = userDAO.getUserByUserName(this.user.getUserName());
        } catch (Exception e) {
            this.message = "Username not found.";
            return "signin.xhtml?faces-redirect=true";
        }
        if (u.getPassword().equals(this.user.getPassword())) {
            this.user = u;
            this.message = "Log-in successful. Welcome to the Flight Reservation System!";
            return "index.xhtml?faces-redirect=true";
        } else {
            this.message = "Wrong password.";
            return "signin.xhtml?faces-redirect=true";
        }
    }

    public String register() {
        userDAO.save(this.user);

        this.message = "Sign-up successful. Please log in to your account.";
        return "signin.xhtml?faces-redirect=true";
    }

    // Getter for user.
    public User getUser() {
        return user;
    }

    // Setter for user.
    public void setUser(User user) {
        this.user = user;
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
