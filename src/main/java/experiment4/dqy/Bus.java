package experiment4.dqy;

import experiment2.dqy.Model.Car;
import experiment4.dqy.util.LinkList;

public class Bus {
    private String no;
    private LinkList busLine = new LinkList();

    @Override
    public boolean equals(Object bus) {
        Bus cur = (Bus) bus;
        if (this.no.equals(cur.no)) {
            return true;
        } else {
            return false;
        }
    }
}
