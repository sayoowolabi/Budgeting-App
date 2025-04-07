import java.util.Scanner;

public class User {
    /**
     * This class represents a user of the budget app.
     * It contains methods to create a new user and validate the usernameInput.
     */

    private Scanner scanner = new Scanner(System.in); // Scanner object for user input
    private String username; // The usernameInput of the user
    // The log file for the user, where their budget data will be saved
    private String userLog;
    // The FileCreator object used to create the log file
    FileCreator filecreator = new FileCreator();

    public User(String username) {
        this.username = username; // Sets the usernameInput of the user
        // this.userLog = filecreator.createFile(); // Calls the method to create a log file
    }

    public String getUsername(){
        return username; // Returns the usernameInput of the user
    }

    public String getUserLog(){
        return userLog; // Returns the log file of the user
    }
    
    public void setUsername(String username){
        this.username = username; // Sets the usernameInput of the user
    }

    public void setUserLog(String userLog){
        this.userLog = userLog; // Sets the log file of the user
    }

    public final String createusername(){
        /**
         * Prompts a new user to input their usernameInput 
         * Existing users will not access this screen and will login instead
         */
        System.out.println("Welcome to the Budget App! Please create a username: ");
        
        while (true) {
            System.out.println("Enter your username: ");
            String usernameInput = scanner.nextLine();
    
            // Validate the usernameInput
            if (usernameInput.isEmpty()) { // Check if the usernameInput is empty
                System.out.println("Username cannot be empty. Please try again.");
            } 
            else if (usernameInput.length() < 3) { // Check if the usernameInput is too short
                System.out.println("Username must be at least 3 characters long. Please try again.");
            } 
            else if (usernameInput.length() > 15) {// Check if the usernameInput is too long
                System.out.println("Username must be at most 15 characters long. Please try again.");
            } 
            else if (!usernameInput.matches("^[a-zA-Z0-9_]+$")) {// Check if the usernameInput contains invalid characters
                System.out.println("Username can only contain letters, numbers, and underscores. Please try again.");
            } 
            else if (usernameInput.equalsIgnoreCase("admin") || 
                       usernameInput.equalsIgnoreCase("root") || 
                       usernameInput.equalsIgnoreCase("user") || 
                       usernameInput.equalsIgnoreCase("guest")) { // Check if the usernameInput is reserved
                System.out.println("The username '" + usernameInput + "' is reserved. Please choose a different username.");
            
            } 
            else { // If the usernameInput is valid, return it
                System.out.println("Username accepted: " + usernameInput);
                return usernameInput;
            }
        }
        
    }
    
}

