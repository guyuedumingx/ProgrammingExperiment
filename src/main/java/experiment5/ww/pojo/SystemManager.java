package experiment5.ww.pojo;

import experiment5.ww.pojo.BaseOpera;

/**
 * 系统管理员接口
 * @author yohoyes
 * @date 2021/5/17 0:48
 */
public interface SystemManager extends BaseOpera {
    /**
     * 注册用户
     * @param no 学号
     * @param pwd 密码
     */
    String register(String no, String pwd);

    /**
     * 删除用户
     * @param no 卡号
     */
    boolean delete(String no);

    /**
     * 查询用户信息
     * @param no 卡号
     */
    Card query(String no);
}
