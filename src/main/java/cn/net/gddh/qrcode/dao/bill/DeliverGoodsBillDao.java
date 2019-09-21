package cn.net.gddh.qrcode.dao.bill;

import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBill;
import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBillSO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 送货单DAO
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年9月20日
 */
@Repository
public interface DeliverGoodsBillDao {
    /**
     * 插入
     * @param entity
     * @return
     */
    int insert(DeliverGoodsBill entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    int update(DeliverGoodsBill entity);

    /**
     * 列表查询
     * @param so
     * @return
     */
    List<DeliverGoodsBill> list(DeliverGoodsBillSO so);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    DeliverGoodsBill getById(Long id);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
