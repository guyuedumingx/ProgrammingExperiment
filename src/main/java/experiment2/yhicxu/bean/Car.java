package experiment2.yhicxu.bean;

// 车类
public class Car {
    private String num; // 车牌号
    private long reach; // 到达时间
    private long leave; // 离开时间

    public Car() {
    }

    public Car(String num, long reach) {
        this.num = num;
        this.reach = reach;
    }

    public Car(String num, long reach, long leave) {
        this.num = num;
        this.reach = reach;
        this.leave = leave;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public long getReach() {
        return reach;
    }

    public void setReach(long reach) {
        this.reach = reach;
    }

    public long getLeave() {
        return leave;
    }

    public void setLeave(long leave) {
        this.leave = leave;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNum().equals(((Car) obj).getNum());
    }

}
