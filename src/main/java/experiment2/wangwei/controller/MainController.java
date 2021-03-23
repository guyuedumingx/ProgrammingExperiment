package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
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

    private Car car = new Car(CarNoUtil.build());

    @FXML
    public void inAction(ActionEvent event) {
        lineAnimation.in(car);
        pane.getChildren().add(car.getShape());
    }

    @FXML
    public void outAction(ActionEvent event) {
        lineAnimation.outPlay(car);
    }
}
