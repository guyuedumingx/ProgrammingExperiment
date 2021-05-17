package experiment5.ww;

/**
 * 系统管理员接口
 * @author yohoyes
 * @date 2021/5/17 0:48
 */
public interface SystemManager extends BaseOpera{
    /**
     * 注册用户
     * @param userName 用户名
     * @param pwd 密码
     */
    void register(String userName, String pwd);

    /**
     * 删除用户
     * @param no 卡号
     */
    void delete(String no);

    /**
     * 查询用户信息
     * @param no 卡号
     */
    void query(String no);
}
