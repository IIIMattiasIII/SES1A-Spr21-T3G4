import au.edu.uts.ap.javafx.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import model.ELMS;

public class ELMSApplication extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("icon.png"));
        ViewLoader.showStage(new ELMS(), "/view/login.fxml", "eLibrary Management System", stage);
    }
}
