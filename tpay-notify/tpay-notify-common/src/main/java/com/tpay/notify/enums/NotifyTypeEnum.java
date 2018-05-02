package com.tpay.notify.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-31 16:22
 **/
public enum NotifyTypeEnum {

    THIRD_CALL_BACK_NOTIFY("第三方回调通知", 1),
    BAK_DATA_NOTIFY("备份通知",2);



    /** 描述 */
    private String desc;
    /** 枚举值 */
    private int value;

    /** 构造函数 */
    private NotifyTypeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NotifyTypeEnum getEnum(int value) {
        NotifyTypeEnum resultEnum = null;
        NotifyTypeEnum[] enumAry = NotifyTypeEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (enumAry[i].getValue() == value) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List toList() {
        NotifyTypeEnum[] ary = NotifyTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", String.valueOf(ary[i].getValue()));
            map.put("desc", ary[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args){
      System.out.println(getEnum(1).toString());
    }
}
