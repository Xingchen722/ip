import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lars.Lars;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Lars lars = new Lars("./data/lars.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            MainWindow controller = fxmlLoader.getController();
            controller.setLars(lars);

            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Lars");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
