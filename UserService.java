//Using java.util.Logger;
import java.util.logging.Logger;

public class UserService {
    //Add logging to the validate mail method to log valid and invalid email checks.
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    //Create a validateEmal method that checks if the email mathces a valid regex pattern.
    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = email != null && email.matches(emailRegex);
        
        if (isValid) {
            logger.info(() -> "Valid email: " + email);
        } else {
            logger.info(() -> "Invalid email: " + email);
        }
        
        return isValid;
    }
    
}
