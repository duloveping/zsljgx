package cn.net.gddh.qrcode.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年1月3日
 */
public class EntitySupport implements Serializable {
    private static final long serialVersionUID = -4034740439959514675L;
    /** 流水号 */
    private Long id;
    /** 创建人ID */
    private Long creatorId;
    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 创建时间 */
    private Date createTime;
    /** 编辑人ID */
    private Long editorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 编辑时间 */
    private Date editTime;
    /** 状态 */
    private Boolean status;
    /** 标记 */
    private Boolean flag;

    public EntitySupport() {
    }

    /**
     * 插入之前执行方法，子类实现
     */
    public void preInsert() {
        this.createTime = new Date();
        this.editTime = this.createTime;
        this.flag = Boolean.TRUE;
        this.status = Boolean.TRUE;
    }

    /**
     * 更新之前执行方法，子类实现
     */
    public void preUpdate() {
        this.editTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getEditorId() {
        return editorId;
    }

    public void setEditorId(Long editorId) {
        this.editorId = editorId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}

