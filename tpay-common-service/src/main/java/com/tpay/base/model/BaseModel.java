package com.tpay.base.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-22 15:44
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 6318607351732030424L;

    private Long id;

    private Boolean enable;

    private String remark;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Long createBy;

    private Long updateBy;


    public Long getId() {
        return id;
    }


    public String getTid() {
        return id == null ? "" : id.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
