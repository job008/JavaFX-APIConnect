package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Project: hw-planner
 *
 * @autor Job Stoit
 * @copy 2017
 * @since 01-10-17
 */
public abstract class Controller {

    private Stage priStage;
    protected HashMap<String, Object> mainProps;

    /**
     * initStage (construct)
     * 
     * @param stage Stage
     * @param globalProps HashMap<String, Object> (optional)
     */

    public void initStage(Stage stage, HashMap<String, Object> globalProps){
        this.mainProps = globalProps;
        this.priStage = stage;
    }

    public void initStage(Stage stage){
        this.priStage = stage;
    }

    protected void nextScene(String location){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent scene = loader.load();

            Controller controler = loader.getController();
            controler.initStage(this.priStage, mainProps);

            priStage.getScene().setRoot(scene);

        } catch (IOException e) {
            System.out.println("error - Controller.nextScene :");
            System.out.println(e);
        }

    }

    protected void changeStageTitle(String title){
        this.priStage.setTitle(title);
    }

    protected void closeStage(){
        this.priStage.close();
    }
}
