//Create a validateEmail method that checks if the email matches a valid regex pattern
public class UserService {
    // Add logging to the validateEmail method to log valid and invalid email checks
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
    /**
     * Validates the format of an email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public boolean validateEmailWithLogging(String email) {
        boolean isValid = validateEmail(email);
        if (isValid) {
            logger.info("Valid email: {}", email);
        } else {
            logger.warn("Invalid email: {}", email);
        }
        return isValid;
    }

    // Method to validate email format
    public boolean validateEmail(String email) {
        // Regular expression for validating email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }
    
}
