package experiment2.yhicxu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Map;

public class ShowController {

    @FXML
    private Button back;

    @FXML
    private Label content;

    private Stage stage;

    private Map<String, Object> data;

    private Scene index;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
        data.put("showBox", content);
    }

    public void setIndex(Scene index) {
        this.index = index;
    }

    @FXML
    void backEvent(ActionEvent event) {
        stage.setScene(index);
    }

}

