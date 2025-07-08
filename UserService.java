// Use java.util.Logger instead of org.slf4j.Logger
import java.util.logging.Logger;
class UserService {
    // Add logging to the validateEmail method to log valid and invalid email checks.
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

   // Create a validateEmail method that checks if the email matches a valid regex pattern.
    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = email != null && email.matches(emailRegex);
        
        if (isValid) {
            logger.info("Valid email: " + email);
        } else {
            logger.warning("Invalid email: " + email);
        }
        
        return isValid;
    }   
   
}