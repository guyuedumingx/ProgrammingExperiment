package experiment2.Florence.pojo;

import util.TimeUtil;

/**
 * @author Florence
 */
public class Car {
    private final String carNo;
    private final String arrive;
    private long arriveTime;
    private String leave="还未离开";
    private long leaveTime;

    public Car(long arriveTime,String carNo ) {
        this.arriveTime=arriveTime;
        arrive = TimeUtil.getTimeStrByTimestamp(arriveTime);
        this.carNo=carNo;
    }

    public int getPassTime() {
        return (int) (leaveTime-arriveTime)/60000;
    }

    public String getCarNo() {
        return carNo;
    }

    public long getArrive() {
        return arriveTime;
    }

    public void setArrive(long arrive) {
        this.arriveTime = arrive;
        leave=TimeUtil.getTimeStrByTimestamp(arrive);
    }

    public long getLeave() {
        return leaveTime;
    }

    public void setLeave(long leave) {
        this.leaveTime = leave;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNo='" + carNo + '\'' +
                ", arrive=" + arrive +
                ", leave=" + leave +
                '}';
    }
}
