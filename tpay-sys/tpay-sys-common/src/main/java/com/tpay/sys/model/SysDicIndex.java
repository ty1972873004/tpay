package com.tpay.sys.model;

import com.tpay.base.model.BaseModel;



/**
* @desc 字典表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
public class SysDicIndex extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long catalogId;
    /**
     * 字段key
     */
    private String keyValue;
    /**
     * 名称
     */
    private String keyName;


    public Long getCatalogId() {
        return catalogId;
    }
    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}