package cn.net.gddh.qrcode.controller.bill;

import cn.net.gddh.qrcode.controller.BaseController;
import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBill;
import cn.net.gddh.qrcode.entity.bill.DeliverGoodsBillSO;
import cn.net.gddh.qrcode.service.DeliverGoodsBillService;
import cn.net.gddh.qrcode.utils.QRCodeUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/bill/deliver-goods-bill")
public class DeliverGoodsBillController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliverGoodsBillController.class);
    @Autowired
    private DeliverGoodsBillService deliverGoodsBillService;
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("bill/deliver-goods-bill/index");
    }

    @PostMapping("list")
    @ResponseBody
    public JSONObject list(DeliverGoodsBillSO so){
        PageInfo<DeliverGoodsBill> pageInfo = deliverGoodsBillService.findPage(so);
        List<DeliverGoodsBill> datas = pageInfo.getList();

        JSONObject json = resultSuccess();
        json.put("datas", datas);
        json.put("total", pageInfo.getTotal());
        json.put("pages", pageInfo.getPages());
        return json;
    }

    @GetMapping("single-edit")
    public ModelAndView singleEdit(@RequestParam(value = "id", required = false) Long id) {
        DeliverGoodsBill so = new DeliverGoodsBill();
        ModelAndView mv = new ModelAndView("bill/deliver-goods-bill/single-edit");
        if (null != id) {
            so = deliverGoodsBillService.getById(id);
        }
        mv.getModel().put("so", so);
        return mv;
    }

    @PostMapping("single-save")
    @ResponseBody
    public JSONObject singleSave(@RequestBody DeliverGoodsBillSO so){
        DeliverGoodsBill po = new DeliverGoodsBill();
        if (null != so.getId()) {
            po = deliverGoodsBillService.getById(so.getId());
        }
        po.setProviderCode(StringUtils.trimToNull(so.getProviderCode()));
        po.setProviderName(StringUtils.trimToNull(so.getProviderName()));
        po.setProductCode(StringUtils.trimToNull(so.getProductCode()));
        po.setProductName(StringUtils.trimToNull(so.getProductName()));
        po.setMaterielCode(StringUtils.trimToNull(so.getMaterielCode()));
        po.setMaterielName(StringUtils.trimToNull(so.getMaterielName()));
        po.setMaterielSpec(StringUtils.trimToNull(so.getMaterielSpec()));
        po.setAmount(StringUtils.trimToNull(so.getAmount()));
        po.setUnit(StringUtils.trimToNull(so.getUnit()));
        po.setBatchCode(StringUtils.trimToNull(so.getBatchCode()));
        po.setProduceDate(so.getProduceDate());
        po.setWorkShiftCode(StringUtils.trimToNull(so.getWorkShiftCode()));
        po.setWorkShiftName(StringUtils.trimToNull(so.getWorkShiftName()));
        po.setCaption(StringUtils.trimToNull(so.getCaption()));
        if (null != so.getId()) {
            deliverGoodsBillService.update(po);
        } else {
            deliverGoodsBillService.insert(po);
        }
        JSONObject json = resultSuccess();
        json.put("id", po.getId());
        return json;
    }

    @GetMapping("single-delete")
    @ResponseBody
    public JSONObject singleDelete(@RequestParam("id") Long id){
        deliverGoodsBillService.deleteById(id);
        return resultSuccess();
    }

    @GetMapping("single-print")
    public ModelAndView singlePrint(@RequestParam("id") Long id) {
        DeliverGoodsBill so = new DeliverGoodsBill();
        ModelAndView mv = new ModelAndView("bill/deliver-goods-bill/single-print");
        if (null != id) {
            so = deliverGoodsBillService.getById(id);
        }
        mv.getModel().put("so", so);
        return mv;
    }

    @GetMapping("multi-edit")
    public ModelAndView multiEdit() {
        ModelAndView mv = new ModelAndView("bill/deliver-goods-bill/multi-edit");
        return mv;
    }

    @RequestMapping("multi-save")
    @ResponseBody
    public JSONObject multiSave(@RequestBody DeliverGoodsBillSO billSO){
        List<Long> ids = new ArrayList<Long>();
        if (null != billSO.getBillList() && billSO.getBillList().size() > 0) {
            for (DeliverGoodsBill so : billSO.getBillList()) {
                DeliverGoodsBill po = new DeliverGoodsBill();
                po.setProviderCode(StringUtils.trimToNull(so.getProviderCode()));
                po.setProviderName(StringUtils.trimToNull(so.getProviderName()));
                po.setProductCode(StringUtils.trimToNull(so.getProductCode()));
                po.setProductName(StringUtils.trimToNull(so.getProductName()));
                po.setMaterielCode(StringUtils.trimToNull(so.getMaterielCode()));
                po.setMaterielName(StringUtils.trimToNull(so.getMaterielName()));
                po.setMaterielSpec(StringUtils.trimToNull(so.getMaterielSpec()));
                po.setBatchCode(StringUtils.trimToNull(so.getBatchCode()));
                po.setProduceDate(so.getProduceDate());
                po.setWorkShiftCode(StringUtils.trimToNull(so.getWorkShiftCode()));
                po.setWorkShiftName(StringUtils.trimToNull(so.getWorkShiftName()));
                po.setUnit(StringUtils.trimToNull(so.getUnit()));
                po.setAmount(StringUtils.trimToNull(so.getAmount()));
                po.setCaption(StringUtils.trimToNull(so.getCaption()));
                deliverGoodsBillService.insert(po);
                ids.add(po.getId());
            }
        }
        JSONObject json = resultSuccess();
        json.put("ids", ids);
        return json;
    }

    @GetMapping("multi-print")
    public ModelAndView multiPrint(@RequestParam("ids") Long[] ids) {
        DeliverGoodsBillSO so = new DeliverGoodsBillSO();
        so.setIds(Arrays.asList(ids));
        List<DeliverGoodsBill> list = deliverGoodsBillService.list(so);
        ModelAndView mv = new ModelAndView("bill/deliver-goods-bill/multi-print");
        mv.getModel().put("list", list);
        return mv;
    }


    @RequestMapping(value = "qrcode", method = RequestMethod.GET)
    @ResponseBody
    public void qrcode(@RequestParam Long id, HttpServletResponse response) {
        DeliverGoodsBill so = deliverGoodsBillService.getById(id);
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.trimToEmpty(so.getProviderName()));
        if (null != so.getProduceDate()) {
            sb.append(DateFormatUtils.format(so.getProduceDate(), "yyyy年M月d日"));
        }
        if (StringUtils.isNotBlank(so.getWorkShiftName())) {
            sb.append(StringUtils.trimToEmpty(so.getWorkShiftName()) + "班");
        }
        sb.append("生产的");
        if (StringUtils.isNotBlank(so.getMaterielCode())) {
            sb.append("料号为：" + StringUtils.trimToEmpty(so.getMaterielCode()));
        }
        if (StringUtils.isNotBlank(so.getBatchCode())) {
            sb.append("，批次号为：" + StringUtils.trimToEmpty(so.getBatchCode()));
        }
        if (StringUtils.isNotBlank(so.getMaterielName())) {
            sb.append("的" + StringUtils.trimToEmpty(so.getMaterielName()));
        }
        if (StringUtils.isNotBlank(so.getMaterielSpec())) {
            sb.append("，规格型号：" + StringUtils.trimToEmpty(so.getMaterielSpec()));
        }
        if (StringUtils.isNotBlank(so.getAmount())) {
            sb.append("，数量为：" + StringUtils.trimToEmpty(so.getAmount()));
            if (StringUtils.isNotBlank(so.getUnit())) {
                sb.append(StringUtils.trimToEmpty(so.getUnit()));
            }
        }
        try {
            // 生成二维码
            BitMatrix qRcodeImg = QRCodeUtils.generateQRCodeStream(sb.toString(), response);
            // 将二维码输出到页面中
            MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @GetMapping("multi-delete")
    @ResponseBody
    public JSONObject multiDelete(@RequestParam("ids") Long[] ids){
        if (null != ids && ids.length > 0) {
            for (Long id : ids) {
                deliverGoodsBillService.deleteById(id);
            }
        }
        return resultSuccess();
    }
}

