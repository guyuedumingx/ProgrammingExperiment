package experiment2.dqy.Model;

import java.util.Date;

public class Car {
    private String no;
    private String name;
    private Long enterTime;
    private Long leavingTime;

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

    public Long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Long enterTime) {
        this.enterTime = enterTime;
    }

    public Long getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Long leavingTime) {
        this.leavingTime = leavingTime;
    }

    public Car() {};

    public Car(String no, String name, Long enterTime) {
        this.no = no;
        this.name = name;
        this.enterTime = enterTime;
        this.leavingTime = null;
    }

    @Override
    public boolean equals(Object car) {
        Car cur = (Car) car;
        if (this.no.equals(cur.no)) {
            return true;
        } else {
            return false;
        }
    }
}
