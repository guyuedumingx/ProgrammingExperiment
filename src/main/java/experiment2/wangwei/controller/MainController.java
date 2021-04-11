package experiment2.wangwei.controller;

import experiment2.wangwei.pojo.Car;
import experiment2.wangwei.pojo.CarPort;
import experiment2.wangwei.pojo.Lane;
import experiment2.wangwei.pojo.Park;
import experiment2.wangwei.utils.WQueue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
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

    private Lane lane = Lane.getLane();
    private Park carPark = new Park(65,330,245);
    private Park tempPark = new Park(65, 330, 375);
    CarPort out = null;
    private WQueue<CarPort> tempQueue = new WQueue<>();
    private boolean access = true;

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

    private CarPort getCarPort(String carNo) {
        CarPort out = null;
        while (!(out = carPark.out()).getCar().equals(carNo)) {
            tempPark.in(out);
        }
        return out;
    }

    CarPort carOut = null;
    private void temParkAction(String carNo) {
        if(carPark.search(carNo) != -1) {
            Car car = getCarPort(carNo).getCar();
            car.moveTo(600, car.getY());
            car.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    while (!tempPark.isEmpty()) {
                        carOut = tempPark.out();
                        carPark.in(carOut);
                        setCarOutAction(carOut.getCar());
                    }
                    System.out.println("carOut : " + carOut.getCar().getNo());
                    if(carOut != null){
                        carOut.getCar().setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Car landOut = null;
                                while (!carPark.isFull() && !lane.isEmpty()) {
                                    landOut = lane.out();
                                    carPark.in(landOut);
                                    setCarOutAction(landOut);
                                }
                                carOut.getCar().removeFinishedAction();
                            }
                        });
                    }else{
                        while (!carPark.isFull() && !lane.isEmpty()) {
                            Car landOut = lane.out();
                            carPark.in(landOut);
                            setCarOutAction(landOut);
                        }
                    }
                }
            });
        }
    }

    @FXML
    public void outAction(ActionEvent event) {
//        Car car = CarParkAnimation.outPlay();
    }
}
