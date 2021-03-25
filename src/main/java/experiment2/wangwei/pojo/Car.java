package experiment2.wangwei.pojo;

import experiment2.wangwei.utils.ColorUtil;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 汽车类
 * @author yohoyes
 */
public class Car extends BaseShape{
    //车牌号
    private String no;

    public Car(String no) {
        this.no = no;
        x = 50;
        y = 75;
        width = 30;
        height = 30;

        Rectangle rectParallel = new Rectangle(10,1, width, height);
        rectParallel.setArcHeight(15);
        rectParallel.setArcWidth(15);
        rectParallel.setFill(ColorUtil.getRamdonColor());
        rectParallel.setTranslateX(x);
        rectParallel.setTranslateY(y);
        this.shape = rectParallel;
        addAction();
    }

    public String getNo() {
        return no;
    }

    @Override
    public boolean equals(Object obj) {
        String carNo = (String)obj;

        return this.no.equals(carNo);
    }

    private void addAction() {
//        shape.setOnMouseClicked((event -> {
////            CarParkAnimation.outPlay(getNo());
//        }));
    }
}
