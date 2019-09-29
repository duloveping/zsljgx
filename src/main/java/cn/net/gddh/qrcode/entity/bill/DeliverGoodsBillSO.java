package cn.net.gddh.qrcode.entity.bill;

import cn.net.gddh.qrcode.entity.SOSupport;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 送货单
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年9月20日
 */
public class DeliverGoodsBillSO extends SOSupport {
    /** 供应商编码 */
    private String providerCode;
    /** 供应商名称 */
    private String providerName;
    /** 物料编码 */
    private String materielCode;
    /** 物料名称 */
    private String materielName;
    /** 规格型号 */
    private String materielSpec;
    /** 数量 */
    private String amount;
    /** 单位 */
    private String unit;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 工厂编码 */
    private String factoryCode;
    /** 工厂名称 */
    private String factoryName;
    /** 车间编码 */
    private String workShopCode;
    /** 车间名称 */
    private String workShopName;
    /** 班次编码 */
    private String workShiftCode;
    /** 班次名称 */
    private String workShiftName;
    /** 生产日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date produceDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startProduceDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endProduceDate;
    /** 生产数量 */
    private Integer produceAmount;
    /** 发货日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliverDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDeliverDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDeliverDate;
    /** 发货数量 */
    private Integer deliverAmount;
    /** 批号号 */
    private String batchCode;
    private String caption;
    private List<DeliverGoodsBill> billList;

    public DeliverGoodsBillSO() {
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getMaterielCode() {
        return materielCode;
    }

    public void setMaterielCode(String materielCode) {
        this.materielCode = materielCode;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getMaterielSpec() {
        return materielSpec;
    }

    public void setMaterielSpec(String materielSpec) {
        this.materielSpec = materielSpec;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkShopCode() {
        return workShopCode;
    }

    public void setWorkShopCode(String workShopCode) {
        this.workShopCode = workShopCode;
    }

    public String getWorkShopName() {
        return workShopName;
    }

    public void setWorkShopName(String workShopName) {
        this.workShopName = workShopName;
    }

    public String getWorkShiftCode() {
        return workShiftCode;
    }

    public void setWorkShiftCode(String workShiftCode) {
        this.workShiftCode = workShiftCode;
    }

    public String getWorkShiftName() {
        return workShiftName;
    }

    public void setWorkShiftName(String workShiftName) {
        this.workShiftName = workShiftName;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public Date getStartProduceDate() {
        return startProduceDate;
    }

    public void setStartProduceDate(Date startProduceDate) {
        this.startProduceDate = startProduceDate;
    }

    public Date getEndProduceDate() {
        return endProduceDate;
    }

    public void setEndProduceDate(Date endProduceDate) {
        this.endProduceDate = endProduceDate;
    }

    public Integer getProduceAmount() {
        return produceAmount;
    }

    public void setProduceAmount(Integer produceAmount) {
        this.produceAmount = produceAmount;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Date getStartDeliverDate() {
        return startDeliverDate;
    }

    public void setStartDeliverDate(Date startDeliverDate) {
        this.startDeliverDate = startDeliverDate;
    }

    public Date getEndDeliverDate() {
        return endDeliverDate;
    }

    public void setEndDeliverDate(Date endDeliverDate) {
        this.endDeliverDate = endDeliverDate;
    }

    public Integer getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(Integer deliverAmount) {
        this.deliverAmount = deliverAmount;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public List<DeliverGoodsBill> getBillList() {
        return billList;
    }

    public void setBillList(List<DeliverGoodsBill> billList) {
        this.billList = billList;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
