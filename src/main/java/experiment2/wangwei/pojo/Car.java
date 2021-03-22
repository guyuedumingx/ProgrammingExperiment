package experiment2.wangwei.pojo;

/**
 * 汽车类
 * @author yohoyes
 */
public class Car {
    //车牌号
    private int no;

    public Car(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object obj) {
        Car car = (Car)obj;
        return this.no == car.no;
    }

    @Override
    public String toString() {
        return "Car{" +
                "no=" + no +
                '}';
    }
}
