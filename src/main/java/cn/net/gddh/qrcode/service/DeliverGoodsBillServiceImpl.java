package cn.net.gddh.qrcode.service;

import cn.net.gddh.qrcode.dao.bill.DeliverGoodsBillDao;
import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBill;
import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBillSO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 送货单Service
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年9月20日
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DeliverGoodsBillServiceImpl implements DeliverGoodsBillService {
    @Autowired
    private DeliverGoodsBillDao deliverGoodsBillDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int insert(DeliverGoodsBill entity) {
        entity.preInsert();
        return deliverGoodsBillDao.insert(entity);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int update(DeliverGoodsBill entity) {
        entity.preUpdate();
        return deliverGoodsBillDao.update(entity);
    }

    @Override
    public List<DeliverGoodsBill> list(DeliverGoodsBillSO so) {
        return deliverGoodsBillDao.list(so);
    }

    @Override
    public PageInfo<DeliverGoodsBill> findPage(DeliverGoodsBillSO so) {
        PageHelper.startPage(so.getPageNum(), so.getPageSize());
        PageInfo pageInfo = new PageInfo(deliverGoodsBillDao.list(so));
        return pageInfo;
    }

    @Override
    public DeliverGoodsBill getById(Long id) {
        if (null == id) {
            throw new NullPointerException("id is null");
        }
        return deliverGoodsBillDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int deleteById(Long id) {
        if (null == id) {
            throw new NullPointerException("id is null");
        }
        return deliverGoodsBillDao.deleteById(id);
    }
}
