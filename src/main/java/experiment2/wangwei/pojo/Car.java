package experiment2.wangwei.pojo;

/**
 * 汽车类
 * @author yohoyes
 */
public class Car {
    //车牌号
    private String no;

    public Car(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car)obj;
        return this.no.equals(car.no);
    }

    @Override
    public String toString() {
        return "Car{" +
                "no=" + no +
                '}';
    }
}
