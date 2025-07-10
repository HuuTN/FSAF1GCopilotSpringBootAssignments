// Task 2 : Context-Driven Suggestion

// use java.util.logging.Logger instead of org.slf4j.Logger;
import java.util.logging.Logger;

public class UserService {
    //Add logging to the validateEmail method to log valid and invalid email checks
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    
    
    /**
     * Validates the format of an email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            logger.warning("Invalid email: Email is null or empty");
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = email.matches(emailRegex);
        if (isValid) {
            logger.info("Valid email: " + email);
        } else {
            logger.warning("Invalid email format: " + email);
        }
        return isValid;
    }
}
