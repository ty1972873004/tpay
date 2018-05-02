package com.tpay.payment.utils;

import java.util.Random;


/**
 * @desc 随机字符串生成
 * @author Trazen
 * @since 2018-04-11
 * @version 1.0
 */
public class RandomStringUtils {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    public static void main(String[] args){
    	System.out.println(getRandomStringByLength(32));
    }
	
	/**
	 * 产生0～max的随机整数 包括0 不包括max
	 * 
	 * @param max
	 *            随机数的上限
	 * @return
	 */
	public static int getRandom(int max) {
		return new Random().nextInt(max);
	}

	/**
	 * 产生 min～max的随机整数 包括 min 但不包括 max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		int r = getRandom(max - min);
		return r + min;
	}

}
