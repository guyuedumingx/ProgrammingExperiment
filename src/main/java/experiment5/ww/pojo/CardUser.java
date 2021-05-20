package experiment5.ww.pojo;

import experiment5.ww.pojo.BaseOpera;

/**
 * 卡用户接口
 * @author yohoyes
 * @date 2021/5/17 0:48
 */
public interface CardUser extends BaseOpera {

    /**
     * 充值
     * @param money 钱数
     */
    boolean topUp(int money);

    /**
     * 消费
     * @param money 钱数
     */
    boolean consumption(int money);

    /**
     * 查询卡余额
     */
    double balance();
}
