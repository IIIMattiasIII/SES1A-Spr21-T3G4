import au.edu.uts.ap.javafx.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import model.ELMS;

public class ELMSApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setX(50);
        stage.setY(50);
        stage.getIcons().add(new Image("icon.png"));
        ViewLoader.showStage(new ELMS(), "/view/Login.fxml", "eLibrary Management System", stage);
    }
}
