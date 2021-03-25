package experiment2.wangwei.pojo;

/**
 * 车位
 * 像车位，但不是车位，像是把车包起来的包裹
 * @author yohoyes
 */
public class CarPort {
    private Car car;
    //车编号
    private String no;
    //停车时间
    private long parkTime;

    public CarPort(Car car) {
        this.car = car;
        this.no = car.getNo();
        this.parkTime = System.currentTimeMillis();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public long getParkTime() {
        return parkTime;
    }

    public void setParkTime(long parkTime) {
        this.parkTime = parkTime;
    }

    @Override
    public boolean equals(Object obj) {
        String no = (String) obj;
        return this.no.equals(no);
    }
}

