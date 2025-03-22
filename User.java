import java.util.Scanner;

public class User {

    String username;
    String userLog;
    FileCreator filecreator = new FileCreator();

    public User(){
        this.username = createUserName();
        this.userLog = filecreator.createFile();
    }
    

    public String createUserName(){
        /**
         * Prompts a new user to input their username 
         * Existing users will not access this screen and will login instead
         */
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        scanner.close();

        return username;
    }
}
