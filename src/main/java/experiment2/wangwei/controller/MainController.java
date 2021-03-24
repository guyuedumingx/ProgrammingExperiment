package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.CarPark;
import experiment2.wangwei.pojo.Lane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import util.CarNoUtil;

/**
 * @author yohoyes
 * @date 2021/3/23 20:57
 */
public class MainController {
    @FXML
    private Button inBtn;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button outBtn;

    private LineAnimation lineAnimation = LineAnimation.getAnimation();
    @FXML
    public void inAction(ActionEvent event) {
        Car car = new Car(CarNoUtil.build());
        lineAnimation.in(car);
        pane.getChildren().add(car.getShape());
    }

    @FXML
    public void outAction(ActionEvent event) {
        Car car = lineAnimation.outPlay();
    }
}
