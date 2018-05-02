package com.tpay.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-16 17:34
 **/
public enum PayChannelEnum {

    WX_JSAPI("微信公众号支付", "WX_JSAPI"),

    WX_APP("微信APP支付", "WX_APP"),

    WX_NATIVE("微信扫码支付","WX_NATIVE"),

    WX_MWEB("微信H5支付", "WX_MWEB"),

    ALIPAY_WAP("支付宝WAP支付", "ALIPAY_WAP"),

    ALIPAY_PC("支付宝PC支付", "ALIPAY_PC"),

    ALIPAY_MOBILE("支付宝移动支付", "ALIPAY_MOBILE"),

    ALIPAY_QR("支付宝扫码支付", "ALIPAY_QR");

    /** 描述 */
    private String desc;
    /** 枚举值 */
    private String value;
    /** 构造函数 */
    private PayChannelEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public static PayChannelEnum getEnum(String value){
        PayChannelEnum resultEnum = null;
        PayChannelEnum[] enumAry = PayChannelEnum.values();
        for(int i = 0;i<enumAry.length;i++){
            if(enumAry[i].getValue().equals(value)){
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PayChannelEnum[] ary = PayChannelEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(ary[num].getValue()));
            map.put("value", String.valueOf(ary[num].getValue()));
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }


    @SuppressWarnings({"unchecked", "rawtypes" })
    public static List toList(){
        PayChannelEnum[] ary = PayChannelEnum.values();
        List list = new ArrayList();
        for(int i=0;i<ary.length;i++){
            Map<String,String> map = new HashMap<String,String>();
            map.put("value",String.valueOf(ary[i].getValue()));
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

    /**
     * 取枚举的json字符串
     * @return
     */
    public static String getJsonStr(){
        PayChannelEnum[] enums = PayChannelEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (PayChannelEnum senum : enums) {
            if(!"[".equals(jsonStr.toString())){
                jsonStr.append(",");
            }
            jsonStr.append("{id:'")
                    .append(senum.toString())
                    .append("',desc:'")
                    .append(senum.getDesc())
                    .append("',value:'")
                    .append(senum.getValue())
                    .append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args){
         System.out.println(PayChannelEnum.getEnum("WX_JSAPI").getDesc());

    }
}
