//Use java.util.Logging for logging purposes
import java.util.logging.Logger;
public class UserService {
    
    // Add Logging to the validateEmail method to log valid an invalid email checks
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    //Create a validateEmail method that checks if the email matches a valid regex pattern
    @SuppressWarnings("LoggerStringConcat")
    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = email.matches(emailRegex);
        
        if (isValid) {
            logger.info("Valid email: " + email);
        } else {
            logger.warning("Invalid email: " + email);
        }
        
        return isValid;
    }
    

}
