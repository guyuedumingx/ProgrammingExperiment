package experiment2.yhicxu.controller;

import experiment2.yhicxu.bean.Car;
import experiment2.yhicxu.carpark.CarPark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Map;

public class InputCarNumberController {

    @FXML
    private Button submit;

    @FXML
    private Button back;

    @FXML
    private TextField inp;

    private CarPark carPark;

    private Stage stage;

    private Scene index;
    private Scene tips;

    private Map<String, Object> data;

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIndex(Scene index) {
        this.index = index;
    }

    public void setTips(Scene tips) {
        this.tips = tips;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public InputCarNumberController() {
//        try {
//            index = new Scene(FXMLLoader.load(getClass().getResource("..\\view\\index.fxml")), 600, 400);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void submitEvent(ActionEvent event) {
        Label tipsBox = (Label) data.get("tipsBox");
        String num = inp.getText();
        String submitState = (String) data.get("submitState");
        if (submitState.equals("in")) {
            if (num.length() == 0) {
                tipsBox.setText("车牌号不能为空");
            } else if (carPark.arrival(inp.getText())) {
                tipsBox.setText("车辆驶入成功");
            } else {
                tipsBox.setText("该车已经在停车场系统中");
            }
        } else if (submitState.equals("out")) {
            Car car;
            if (num.length() == 0) {
                tipsBox.setText("车牌号不能为空");
            } else if ((car = carPark.leave(num)) != null) {
                int time = carPark.getTime(car);
                double price = carPark.charging(car);
                tipsBox.setText("车辆" + num + "停车时长" + time + "分钟\n共收费" + new DecimalFormat("#.00").format(price) + "元");
            } else {
                tipsBox.setText("停车场中没有车牌号为" + num + "的车");
            }
        } else {
            new Exception("bug").printStackTrace();
        }
        inp.setText("");
        stage.setScene(tips);
    }

    @FXML
    void backEvent(ActionEvent event) {
        inp.setText("");
        stage.setScene(index);
    }

}

