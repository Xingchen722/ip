import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lars.Lars;
import lars.exceptions.LarsException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lars lars;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/me.png"));
    private Image larsImage = new Image(this.getClass().getResourceAsStream("/images/oppo.png"));
    private boolean shouldAutoScroll = true;

    /**
     * Initializes auto-scroll behavior for the dialog pane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            shouldAutoScroll = newVal.doubleValue() >= 0.98;
        });

        dialogContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (shouldAutoScroll) {
                scrollPane.setVvalue(1.0);
            }
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws LarsException {
        String input = userInput.getText();
        String response = lars.getResponse(input);
        String commandType = lars.getCommandType();
        DialogBox larsDialog = DialogBox.getLarsDialog(response, larsImage, commandType);

        if ("Error".equals(commandType)) {
            larsDialog.flipToErrorStyle();
        }

        shouldAutoScroll = true;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                larsDialog
        );
        userInput.clear();
        Platform.runLater(() -> scrollPane.setVvalue(1.0));

        if ("ExitCommand".equals(commandType)) {
            javafx.animation.PauseTransition delay =
                    new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
    }

    /**
     * Injects the Lars instance and displays the welcome message.
     */
    public void setLars(Lars lars) {
        this.lars = lars;
        String welcomeText = lars.getWelcome();

        shouldAutoScroll = true;
        dialogContainer.getChildren().addAll(
                DialogBox.getLarsDialog(welcomeText, larsImage, "Welcome")
        );
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles the remind action and appends the reminder dialog.
     */
    @FXML
    private void handleRemind() {
        String response = lars.getResponse("remind");
        shouldAutoScroll = true;
        dialogContainer.getChildren().addAll(
                DialogBox.getLarsDialog(response, larsImage, "reminder")
        );
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }
}

