public class Main {
    public static void main(String[] args){
        BudgetApp budgetApp = new BudgetApp(); // Creates a new BudgetApp object
        budgetApp.loadUserListFromFile(); // Calls the method to load the user list from a file
        budgetApp.login(); // Calls the method to login an existing user

        budgetApp.saveUserListToFile(); // Calls the method to save the user list to a file

    }
    
}
