package com.tpay.user.model;
import com.tpay.base.model.BaseModel;

import java.io.Serializable;



/**
* @desc 商户信息表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class MchInfo extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private String mchId;
    
    /**
     * 商户名称
     */
    private String mchName;
    /**
     * 商户类型
     */
    private String mchType;
    /**
     * 商户私钥
     */
    private String mchPrivateKey;
    /**
     * 商户公钥
     * */
    private String mchPublicKey;
    /**
     * 平台私钥
     * */
    private String tpPrivateKey;
    /**
     * 平台公钥
     * */
    private String tpPublicKey;
    /**
     * 状态 0停用 1正常
     */
    private Integer mchStatus;


    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchName() {
        return mchName;
    }
    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getMchType() {
        return mchType;
    }
    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public String getMchPrivateKey() {
        return mchPrivateKey;
    }

    public void setMchPrivateKey(String mchPrivateKey) {
        this.mchPrivateKey = mchPrivateKey;
    }

    public String getMchPublicKey() {
        return mchPublicKey;
    }

    public void setMchPublicKey(String mchPublicKey) {
        this.mchPublicKey = mchPublicKey;
    }

    public String getTpPrivateKey() {
        return tpPrivateKey;
    }

    public void setTpPrivateKey(String tpPrivateKey) {
        this.tpPrivateKey = tpPrivateKey;
    }

    public String getTpPublicKey() {
        return tpPublicKey;
    }

    public void setTpPublicKey(String tpPublicKey) {
        this.tpPublicKey = tpPublicKey;
    }

    public Integer getMchStatus() {
        return mchStatus;
    }
    public void setMchStatus(Integer mchStatus) {
        this.mchStatus = mchStatus;
    }

    @Override
    public String toString() {
        return "MchInfo{" +
                "mchId='" + mchId + '\'' +
                ", mchName='" + mchName + '\'' +
                ", mchType='" + mchType + '\'' +
                ", mchPrivateKey='" + mchPrivateKey + '\'' +
                ", mchPublicKey='" + mchPublicKey + '\'' +
                ", tpPrivateKey='" + tpPrivateKey + '\'' +
                ", tpPublicKey='" + tpPublicKey + '\'' +
                ", mchStatus=" + mchStatus +
                '}';
    }
}