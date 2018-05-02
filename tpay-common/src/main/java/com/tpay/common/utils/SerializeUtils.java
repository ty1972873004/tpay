package com.tpay.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * @desc 它的作用就是把对象转化为byte数组，或把byte数组转化为对象。
 * @author Administrator
 * @since 2017/6/15
 * @version 1.0
 */



public class SerializeUtils {


    public static byte[] serialize(Object object) {
        if(object==null){
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
       //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }



    public static Object deserialize(byte[] bytes) {
        if(bytes==null){
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
        //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }

}
