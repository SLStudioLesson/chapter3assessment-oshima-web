import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");

            String choice = reader.readLine();
            
            DataHandler dataHandler;
            String currentMode;

            if (choice.equals("1")) {
                dataHandler = new CSVDataHandler();
                currentMode = "CSV";
            } else if (choice.equals("2")) {
                dataHandler = new JSONDataHandler();
                currentMode = "JSON";
            } else {
                dataHandler = new CSVDataHandler();
                currentMode = "CSV";
            }

            System.out.println("Current mode: " + currentMode);

            RecipeUI ui = new RecipeUI(dataHandler);
            ui.displayMenu();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}