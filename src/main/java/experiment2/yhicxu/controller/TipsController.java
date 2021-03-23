package experiment2.yhicxu.controller;

import experiment2.yhicxu.carpark.CarPark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Map;

public class TipsController {

    @FXML
    private Button back;

    @FXML
    private Label content;

    private CarPark carPark;

    private Stage stage;

    private Map<String, Object> data;

    private Scene index;
    private Scene inputCarNumber;

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
        data.put("tipsBox", content);
    }

    public void setIndex(Scene index) {
        this.index = index;
    }

    public void setInputCarNumber(Scene inputCarNumber) {
        this.inputCarNumber = inputCarNumber;
    }

    @FXML
    void backEvent(ActionEvent event) {
        stage.setScene(index);
    }
}

