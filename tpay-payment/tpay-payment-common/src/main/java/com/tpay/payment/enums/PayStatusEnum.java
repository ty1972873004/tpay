package com.tpay.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-01-31 14:41
 **/
public enum PayStatusEnum {


    PAY_EXPIRED("订单过期", "05"),

    PAY_FAILED("支付失败", "04"),

    PAY_INIT("初始化","00"),

    PAY_PAYING("支付中", "01"),

    PAY_SUCCESS("支付成功", "02"),

    PAY_COMPLETE("支付完成", "03");



    /** 描述 */
    private String desc;
    /** 枚举值 */
    private String value;
    /** 构造函数 */
    private PayStatusEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PayStatusEnum getEnum(String value){
        PayStatusEnum resultEnum = null;
        PayStatusEnum[] enumAry = PayStatusEnum.values();
        for(int i = 0;i<enumAry.length;i++){
            if(enumAry[i].getValue().equals(value)){
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PayStatusEnum[] ary = PayStatusEnum.values();
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
        PayStatusEnum[] ary = PayStatusEnum.values();
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
        PayStatusEnum[] enums = PayStatusEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (PayStatusEnum senum : enums) {
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

    public static void main(String[] args){

    }

}
