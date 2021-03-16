package experiment2.wangwei.pojo;

/**
 * 车位
 * 像车位，但不是车位，像是把车包起来的包裹
 * @author yohoyes
 */
public class CarPort {
    private Car car;
    //车编号
    private int no;
    //停车场给这部车的编号
    private int serialNumber;
    //停车时间
    private long parkTime;

    public CarPort(Car car, int serialNumber) {
        this.car = car;
        this.no = car.getNo();
        this.serialNumber = serialNumber;
        this.parkTime = System.currentTimeMillis();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getParkTime() {
        return parkTime;
    }

    public void setParkTime(long parkTime) {
        this.parkTime = parkTime;
    }

    @Override
    public boolean equals(Object obj) {
        int no = (Integer) obj;
        return this.no == no;
    }

    @Override
    public String toString() {
        return "Parking {\n"
                + car.toString() +
                "\n serialNumber=" + serialNumber +
                "\n parkTime=" + parkTime +
                "\n}";
    }
}

