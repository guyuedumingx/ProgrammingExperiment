package experiment5.ww.db;

import experiment5.ww.pojo.Card;

import java.util.List;

/**
 * 数据库接口类
 * @author yohoyes
 * @date 2021/5/19 17:02
 */
public interface Database {

    /**
     * 插入一个卡
     * @param card
     */
    void insert(Card card);

    /**
     * 根据卡号从数据库删除一个卡
     * @param no 卡号
     */
    boolean deleteByNo(String no);

    /**
     * 更新一个卡
     */
    void update(Card card);

    /**
     * 查询一个卡
     * @param no 卡号
     * @return 卡片类
     */
    Card selectByNo(String no);

    /**
     * 根据卡持有者姓名查询一个卡
     * @param name 持有者姓名
     * @return 卡
     */
    Card selectByName(String name);

    /**
     * 获取所有的卡
     */
    List<Card> selectAll();
}
