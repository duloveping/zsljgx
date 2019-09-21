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
        po.setBatchCode(StringUtils.trimToNull(so.getBatchCode()));
        po.setProduceDate(so.getProduceDate());
        po.setWorkShiftCode(StringUtils.trimToNull(so.getWorkShiftCode()));
        po.setWorkShiftName(StringUtils.trimToNull(so.getWorkShiftName()));
        if (null != so.getId()) {
            deliverGoodsBillService.update(po);
        } else {
            deliverGoodsBillService.insert(po);
        }

        PageInfo<DeliverGoodsBill> pageInfo = deliverGoodsBillService.findPage(so);
        List<DeliverGoodsBill> datas = pageInfo.getList();

        JSONObject json = resultSuccess();
        json.put("datas", datas);
        json.put("total", pageInfo.getTotal());
        json.put("pages", pageInfo.getPages());
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
        sb.append(so.getProviderName());
        sb.append(DateFormatUtils.format(so.getProduceDate(), "yyyy年M月d日"));
        sb.append(so.getWorkShiftName() + "班的，");
        sb.append("批次号为" + so.getBatchCode());
        sb.append("的" + so.getMaterielName());

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

