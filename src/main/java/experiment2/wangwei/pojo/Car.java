package experiment2.wangwei.pojo;

/**
 * 汽车类
 * @author yohoyes
 */
public class Car {
    //车牌号
    private int no;
    //车的昵称
    private String name;
    //车的品牌
    private String brand;

    public Car(int no) {
        this.no = no;
    }

    public Car(int no, String brand) {
        this.no = no;
        this.brand = brand;
    }

    public Car(int no, String brand, String name) {
        this.no = no;
        this.brand = brand;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
