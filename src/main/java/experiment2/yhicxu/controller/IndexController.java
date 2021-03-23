package experiment2.yhicxu.controller;

import experiment2.yhicxu.bean.Car;
import experiment2.yhicxu.carpark.CarPark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Map;

public class IndexController {

    @FXML
    private Button showCar;

    @FXML
    private Button carOut;

    @FXML
    private Button exit;

    @FXML
    private Button carIn;

    private CarPark carPark;

    private Stage stage;

    private Scene inputCarNumber;
    private Scene show;
    private Scene tips;

    private Map<String, Object> data;

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setInputCarNumber(Scene inputCarNumber) {
        this.inputCarNumber = inputCarNumber;
    }

    public void setShow(Scene show) {
        this.show = show;
    }

    public void setTips(Scene tips) {
        this.tips = tips;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public IndexController() {
        try {
            inputCarNumber = new Scene(FXMLLoader.load(getClass().getResource("..\\view\\inputCarNumber.fxml")), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void carInEvent(ActionEvent event) {
        data.put("submitState", "in");
        stage.setScene(inputCarNumber);
    }

    @FXML
    void carOutEvent(ActionEvent event) {
        data.put("submitState", "out");
        stage.setScene(inputCarNumber);
    }

    @FXML
    void showCarEvent(ActionEvent event) {
        Car[] cars = carPark.getParkCar();
        if (cars.length == 0) {
            Label tipsBox = (Label) data.get("tipsBox");
            tipsBox.setText("停车场中还没有车");
            stage.setScene(tips);
        } else {
            Label showBox = (Label) data.get("showBox");
            String res = "Park:\n";
            int index = cars.length;
            for (Car car : cars) {
                res += index + ": " + car.getNum() + "\n";
                index--;
            }
            cars = carPark.getWaitingCar();
            if (cars.length != 0) {
                res += "\nWaiting:\n";
                index = 1;
                for (Car car : cars) {
                    res += index + ": " + car.getNum() + "\n";
                    index++;
                }
            }
            showBox.setText(res);
            stage.setScene(show);
        }
    }

    @FXML
    void exitEvent(ActionEvent event) {
        stage.close();
    }

}
