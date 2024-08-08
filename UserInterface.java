import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * User Interface application with a menu bar to perform various actions.
 */
public class UserInterface extends Application {
    private TextFlow textFlow;
    private ScrollPane scrollPane;

    /**
     * Main method to launch the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage and sets up the UI components.
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Interface with Menu");

        textFlow = new TextFlow();
        scrollPane = new ScrollPane(textFlow);
        scrollPane.setFitToWidth(true);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Options");

        menu.getItems().add(createMenuItem("Print Date and Time", this::printDateTime));
        menu.getItems().add(createMenuItem("Save to Log", this::saveToLog));
        menu.getItems().add(createMenuItem("Change Background Color", this::changeBackgroundColor));
        menu.getItems().add(createMenuItem("Exit", primaryStage::close));

        menuBar.getMenus().add(menu);
        root.setTop(menuBar);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a menu item with a given name and action.
     * @param name The name of the menu item.
     * @param action The action to be performed when the menu item is selected.
     * @return The created MenuItem.
     */
    private MenuItem createMenuItem(String name, Runnable action) {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setOnAction(e -> action.run());
        return menuItem;
    }

    /**
     * Prints the current date and time in the text flow area.
     */
    private void printDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Text text = new Text("Current Date and Time: " + formatter.format(date) + "\n");
        textFlow.getChildren().add(text);
    }

    /**
     * Saves the contents of the text flow area to a log file.
     */
    private void saveToLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            for (var node : textFlow.getChildren()) {
                if (node instanceof Text) {
                    writer.write(((Text) node).getText());
                }
            }
            writer.newLine();
            showAlert(Alert.AlertType.INFORMATION, "Save Log", "Contents saved to log.txt");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Save Log", "Error saving to log.txt: " + e.getMessage());
        }
    }

    /**
     * Changes the background color of the text flow area to a random green hue.
     */
    private void changeBackgroundColor() {
        Random rand = new Random();
        float hue = rand.nextFloat() * 0.2f + 0.25f; // Green hue range around 0.25
        Color randomGreen = Color.hsb(hue * 360, 0.9, 0.9);
        textFlow.setStyle("-fx-background-color: " + colorToHex(randomGreen) + ";");
        showAlert(Alert.AlertType.INFORMATION, "Change Background Color", "Background color changed to: " + colorToHex(randomGreen));
    }

    /**
     * Converts a Color object to a hexadecimal color string.
     * @param color The Color object.
     * @return The hexadecimal color string.
     */
    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    /**
     * Displays an alert with the given type, title, and message.
     * @param alertType The type of the alert.
     * @param title The title of the alert.
     * @param message The message of the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
