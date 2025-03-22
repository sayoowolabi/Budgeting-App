import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.List;

public class FileCreator {
    /**
     * Creates log files for each new users
     */


    public int getSaveData(){
        /**
         * Fetches save data from the save data file
         */

        try (BufferedReader reader = new BufferedReader(new FileReader("saveData.txt"))) {
        String line;
        //while there are still lines to be read
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    //store the data
                    String currentFileCount = parts[1].trim();
                    return Integer.parseInt(currentFileCount);

                }
            }
        }
        catch (IOException e) {
            System.err.println("File couldn't be read");
        }

        return -1;
    }

    public void updateFileCounter(){
        String filePath = "saveData.txt"; // Path to your text file
        String targetLinePrefix = "Current File Counter: "; // Prefix of the line to update

        try {
            // Step 1: Read the file into a list of lines
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Step 2: Find and update the target line
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(targetLinePrefix)) {
                    // Extract the current counter value
                    String currentLine = lines.get(i);
                    int currentCounter = Integer.parseInt(currentLine.substring(targetLinePrefix.length()).trim());

                    // Update the counter
                    currentCounter++;

                    // Replace the line with the updated value
                    lines.set(i, targetLinePrefix + currentCounter);
                    break; // Exit the loop once the line is updated
                }
            }

            // Step 3: Write the updated lines back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();

            System.out.println("File updated successfully!");
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    

    public String createFile() {
        /**
         * Creates a new file with file name "logX" (X being the current number of existing files)
         * File counter is incremented after a new on is created 
         * This file will be passed to its corresponding budget object
         */

        try {
            File newLogFile = new File("log" + Integer.toString(getSaveData()));

            if (newLogFile.createNewFile()) {
                System.out.println("File created: " + newLogFile.getName());
                updateFileCounter();
                return newLogFile.getName();

            }
            else {
                System.out.println("File already exists.");
                return null;
            }
        } 
        
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        
    }

}