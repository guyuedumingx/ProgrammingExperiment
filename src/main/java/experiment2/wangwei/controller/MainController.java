package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.CarPort;
import experiment2.wangwei.pojo.Lane;
import experiment2.wangwei.pojo.Park;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import util.CarNoUtil;

import java.util.Iterator;

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

    private Lane lane = Lane.getLane();
    private Park carPark = new Park(65,330,245);
    private Park tempPark = new Park(65, 330, 375);

    @FXML
    public void inAction(ActionEvent event) {
        Car car = new Car(CarNoUtil.build());
        lane.in(car);
        pane.getChildren().add(car.getShape());
        if(!carPark.isFull()) {
            Car out = lane.out();
            carPark.in(out,0);
            setCarOutAction(car);
        }
    }

    private void setCarOutAction(Car car) {
        Shape shape = car.getShape();
        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(car.getNo());
                temParkAction(car.getNo());
            }
        });
    }

    private void temParkAction(String carNo) {
        if(carPark.search(carNo) != -1) {
            CarPort out = null;
            while (!(out = carPark.out(0)).equals(carNo)) {
                tempPark.in(out,0);
            }
            Car car = out.getCar();
            car.moveTo(600, car.getY());
            int delay = 0;
            while (!tempPark.isEmpty()) {
                CarPort carOut = tempPark.out(delay);
                carPark.in(carOut,0);
                setCarOutAction(carOut.getCar());
                delay += 500;
            }
            delay *= 3;
            while (!carPark.isFull() && !lane.isEmpty()) {
                Car landOut = lane.out();
                carPark.in(landOut,delay);
                setCarOutAction(landOut);
                delay += 500;
            }
        }
    }

    @FXML
    public void outAction(ActionEvent event) {
//        Car car = CarParkAnimation.outPlay();
    }
}
