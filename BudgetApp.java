import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class BudgetApp {
    private Scanner scanner = new Scanner(System.in); // Scanner object for user input
    private User currentUser;
    private String userLog;
    static private HashMap<User, String> userList = new HashMap<>();

    public void saveUserListToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("budgetApp.txt"))) {
        for (User user : userList.keySet()) {
            String username = user.getUsername();
            String logFilePath = user.getUserLog(); // Get the log file path
            writer.write(username + "," + logFilePath); // Write in "username,logFilePath" format
            writer.newLine(); // Add a new line for each user
        }
        System.out.println("User list saved to budgetApp.txt.");
    } catch (IOException e) {
        System.out.println("An error occurred while saving the user list: " + e.getMessage());
    }
}

public void loadUserListFromFile() {
    File file = new File("budgetApp.txt");
    if (!file.exists()) {
        System.out.println("No existing user data found. Starting fresh.");
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(","); // Split the line into username and logFilePath
            if (parts.length == 2) {
                String username = parts[0];
                String logFilePath = parts[1];
                User user = new User(username); // Create a User object with the username
                user.setUserLog(logFilePath);
                userList.put(user, logFilePath); // Add the user and log file to the HashMap
            }
        }
        System.out.println("User list loaded from budgetApp.txt.");
    } catch (IOException e) {
        System.out.println("An error occurred while loading the user list: " + e.getMessage());
    }
}

    public BudgetApp() {
        // FileCreator filecreator = new FileCreator(); // Creates a new FileCreator object
        // filecreator.createUserDataFile(); // Calls the method to create a user data file
    }

    public String getUserLog() {
        return userLog; // Returns the log file of the user
    }

    public String getCurrentUser() {
        return currentUser.getUsername(); // Returns the usernameInput of the user
    }

    public HashMap<User, String> getUserList() {
        return userList; // Returns the user list
    }

    public void login() {
        /**
         * Prompts an existing user to input their usernameInput 
         * Existing users will not access this screen and will login instead
         */
        System.out.println("Welcome back to the Budget App! Please enter your username: ");

        try {
            if (scanner.hasNextLine()) { // Check if input is available
                String usernameInput = scanner.nextLine();
    
                // Validate the usernameInput
                if (usernameInput.isEmpty()) {
                    System.out.println("Username cannot be empty. Please try again.");
                } else {
                    boolean userFound = false;
                    for (User user : userList.keySet()) {
                        if (user.getUsername().equals(usernameInput)) {
                            currentUser = user;
                            System.out.println("Welcome back, " + currentUser.getUsername() + "! Your log file is: " + currentUser.getUserLog());
                            userFound = true;
                            break;
                        }
                    }
    
                    if (!userFound) {
                        System.out.println("Username not found. Please create a new account.");
                        createUser();
                    }
                }
            } else {
                System.out.println("No input provided. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        }
    }

    public void createUser() {
        boolean validUser = false; // Flag to check if the user is valid
    while (!validUser) {
        currentUser = new User(""); // Create a new User object for the new user
        currentUser.setUsername(currentUser.createusername()); // Call the method to create a username for the new user

        boolean usernameExists = false;
        for (User user : userList.keySet()) {
            if (user.getUsername().equals(currentUser.getUsername())) { // Check if the user already exists in the HashMap
                System.out.println("Username already exists. Please try again.");
                usernameExists = true;
                break; // Exit the loop to prompt for a new username
            }
        }

        if (!usernameExists) {
            validUser = true; // Set the flag to true if the username is valid
        }
    }

    FileCreator filecreator = new FileCreator(); // Creates a new FileCreator object
    currentUser.setUserLog(filecreator.createFile()); // Calls the method to create a log file for the new user
    userLog = currentUser.getUserLog(); // Get the log file of the new user

    // Add the new user to the user list
    System.out.println("Welcome, " + currentUser.getUsername() + "! Your log file is: " + currentUser.getUserLog());
    userList.put(currentUser, currentUser.getUserLog()); // Add the new user to the user list
    }
}
