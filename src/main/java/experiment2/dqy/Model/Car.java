package experiment2.dqy.Model;

import java.util.Date;

public class Car {
    private String no;
    private String name;
    private Date enterTime;
    private Date leavingTime;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Date leavingTime) {
        this.leavingTime = leavingTime;
    }

    public Car() {};

    public Car(String no, String name, Date enterTime) {
        this.no = no;
        this.name = name;
        this.enterTime = enterTime;
        this.leavingTime = null;
    }

    public boolean equals(Car car) {
        if (this.no == car.no) {
            return true;
        } else {
            return false;
        }
    }
}
