import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.Controller;
import res.conf.PropertyHandeler;

/**
 * created by Job Stoit at Oktober 1 2017
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        PropertyHandeler mainprops = new PropertyHandeler();

        FXMLLoader mainScene = new FXMLLoader(getClass().getResource("scenes/home/home.fxml"));
        Parent loadedScene = mainScene.load();
        Controller controller =  mainScene.getController();

        controller.initStage(primaryStage);

        primaryStage.setTitle(System.getProperty("projectName") + " - version "+ System.getProperty("version"));
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(System.getProperty("favicon"))));
        primaryStage.setScene(new Scene(loadedScene, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
