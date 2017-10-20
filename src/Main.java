import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.Controller;
import res.conf.PropertyHandeler;
import java.awt.*;

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

        // setting a title
        primaryStage.setTitle(System.getProperty("projectName") + " - version "+ System.getProperty("version"));

        // check for icon
        if (System.getProperty("favicon") != null) {
            try {
                primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(System.getProperty("favicon"))));

                if (System.getProperty("os.name").equals("Mac OS X")){
                    java.awt.Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(System.getProperty("favicon")));
                    com.apple.eawt.Application.getApplication().setDockIconImage(img);
                }

            } catch (Exception e){
                System.out.println("error - "+ getClass().getName() +".start():");
                System.out.println(e);
            }
        }

        primaryStage.setScene(new Scene(loadedScene, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
