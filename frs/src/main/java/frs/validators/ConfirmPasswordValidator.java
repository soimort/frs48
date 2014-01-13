package jfs.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Mort Yao <mort.yao@gmail.com>
 * @version 1.0
 * @since  2014-01-14
 *
 * ConfirmPasswordValidator.
 */
@FacesValidator(value = "confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput passwordComponent = (UIInput)component.getAttributes().get("passwordComponent");
        String password = (String)passwordComponent.getValue();
        String confirmPassword = (String)value;

        if (password != null && !password.equals("")) {
            if (confirmPassword == null || confirmPassword.equals("")) {
                FacesMessage msg = new FacesMessage("Please re-enter your password");
                throw new ValidatorException(msg);
            } else if (!confirmPassword.equals(password)) {
                FacesMessage msg = new FacesMessage("Confirm password is not the same as password");
                throw new ValidatorException(msg);
            }
        }
    }

}
