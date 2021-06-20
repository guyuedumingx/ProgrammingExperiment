package experiment5.ww.db;

import experiment5.ww.pojo.Card;

import java.util.List;

/**
 * 数据库接口类
 * @author yohoyes
 * @date 2021/5/19 17:02
 */
public interface Database<T> {

    /**
     * 插入一列
     */
    void insert(T t);

    /**
     * 根据主键删除一列
     * @param no 主键
     */
    boolean deleteByNo(String no);

    /**
     * 更新一个列
     */
    void update(T t);

    /**
     * 查询
     * @return 卡片类
     */
    T selectByNo(String no);

    /**
     * 获取所有列
     */
    List<T> selectAll();
}
