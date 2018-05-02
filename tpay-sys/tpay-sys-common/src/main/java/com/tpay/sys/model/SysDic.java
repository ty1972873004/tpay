package com.tpay.sys.model;

import com.tpay.base.model.BaseModel;



/**
* @desc 字典详情表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysDic extends BaseModel {


    private static final long serialVersionUID = -3874450918067217456L;
    /**
     * 字典表id
     */
    private Long indexId;
    /**
     *
     */
    private String code;
    /**
     *
     */
    private String codeText;
    /**
     *
     */
    private Integer sortNo;
    /**
     *
     */
    private Integer editable;


    public Long getIndexId() {
        return indexId;
    }
    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeText() {
        return codeText;
    }
    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getEditable() {
        return editable;
    }
    public void setEditable(Integer editable) {
        this.editable = editable;
    }

}