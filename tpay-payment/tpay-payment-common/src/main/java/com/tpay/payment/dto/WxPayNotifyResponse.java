package com.tpay.payment.dto;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.tpay.payment.utils.xml.XStreamCDataConverter;
import com.tpay.payment.utils.xml.XStreamInitializer;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-12 15:55
 **/
@XStreamAlias("xml")
public class WxPayNotifyResponse {
    @XStreamOmitField
    private static final transient String FAIL = "FAIL";
    @XStreamOmitField
    private static final transient String SUCCESS = "SUCCESS";
    @XStreamAlias("return_code")
    @XStreamConverter(XStreamCDataConverter.class)
    private String returnCode;
    @XStreamConverter(XStreamCDataConverter.class)
    @XStreamAlias("return_msg")
    private String returnMsg;

    public WxPayNotifyResponse() {
    }

    public WxPayNotifyResponse(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public static String fail(String msg) {
        WxPayNotifyResponse response = new WxPayNotifyResponse("FAIL", msg);
        XStream xstream = XStreamInitializer.getInstance();
        xstream.autodetectAnnotations(true);
        return xstream.toXML(response);
    }

    public static String success(String msg) {
        WxPayNotifyResponse response = new WxPayNotifyResponse("SUCCESS", msg);
        XStream xstream = XStreamInitializer.getInstance();
        xstream.autodetectAnnotations(true);
        return xstream.toXML(response);
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return this.returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}

