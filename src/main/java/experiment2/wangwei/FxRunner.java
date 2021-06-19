package experiment2.wangwei;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

/**
 * @author yohoyes
 * @date 2021/3/23 21:01
 */
public class FxRunner extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = loadFxml("view/main.fxml");
        AnchorPane pane = (AnchorPane)loader.load();
        Scene scene = new Scene(pane,600,559);
        stage.setScene(scene);
        stage.show();
    }

    public  FXMLLoader loadFxml(String str) {
        URL location = getClass().getResource(str);
        FXMLLoader loader = new FXMLLoader(location);
        loader.setLocation(location);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        return loader;
    }

}
