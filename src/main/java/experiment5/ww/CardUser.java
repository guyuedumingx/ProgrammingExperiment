package experiment5.ww;

/**
 * 卡用户接口
 * @author yohoyes
 * @date 2021/5/17 0:48
 */
public interface CardUser extends BaseOpera{

    /**
     * 充值
     * @param no 卡号
     * @param money 钱数
     */
    void topUp(String no, int money);

    /**
     * 消费
     * @param money 钱数
     */
    void consumption(int money);
}
