package com.tpay.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


/**
 * @desc 大数字工具类，用于精确计算
 * @author Administrator
 * @since 2018-03-22
 * @version 1.0
 */
public class BigDecimalUtils {
	
	private static Logger log = LoggerFactory.getLogger(BigDecimalUtils.class);
	/**
	 * 相加
	 * @param add 加数一
	 * @param add2 加数二
	 * @return 相加后的结果
	 */
	public static BigDecimal add(String add, String add2) {
		log.error("BigDecimal-相加 ："+add +"+" +add2+ "=" + new BigDecimal(add).add(new BigDecimal(add2)));
		return new BigDecimal(add).add(new BigDecimal(add2));
	}
	/**
	 * 相加
	 * @param add
	 * @param add2
	 * @return
	 */
	public static BigDecimal add(BigDecimal add, BigDecimal add2) {
		if(add==null){
			return add2;
		} else if (add2==null){
			return add;
		} else {
			return add.add(add2);
		}
	}
	
	/**
	 * 相加
	 * @param add 加数一
	 * @param add2 加数二
	 * @return 相加后的结果
	 */
	public static BigDecimal add(Double add, Double add2) {
		return new BigDecimal(add.toString()).add(new BigDecimal(add2.toString()));
	}
	
	/**
	 * 相减
	 * @param subbed 被减数
	 * @param sub 减数
	 * @return 相减后的结果
	 */
	public static BigDecimal subtract(String subbed, String sub) {
		return new BigDecimal(subbed).subtract(new BigDecimal(sub));
	}
	
	/**
	 * 相减
	 * @param subbed
	 * @param sub
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal subbed, BigDecimal sub) {
		if(subbed==null){
			return sub;
		} else if (sub==null){
			return subbed;
		} else {
			return subbed.subtract(sub);
		}
		
	}
	
	/**
	 * 相减
	 * @param subbed 被减数
	 * @param sub 减数
	 * @return 相减后的结果
	 */
	public static BigDecimal subtract(Double subbed, Double sub) {
		return new BigDecimal(subbed.toString()).subtract(new BigDecimal(sub.toString()));
	}
	
	/**
	 * 相乘
	 * @param mulled 被乘数
	 * @param mul 乘数
	 * @return 相乘后的结果
	 */
	public static BigDecimal multiply(String mulled, String mul) {
		return new BigDecimal(mulled).multiply(new BigDecimal(mul));
	}
	
	/**
	 * 相乘
	 * @param mulled
	 * @param mul
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal mulled, BigDecimal mul) {
		return mulled.multiply(mul);
	}
	
	/**
	 * 相除
	 * @param divided 被除数
	 * @param divide 除数
	 * @return 相除后的结果
	 * modify by xuchaoying
	 * 2013-04-27
	 * 修改相除时精度为2，且四舍五入
	 */
	public static BigDecimal divide(String divided, String divide) {
		return new BigDecimal(divided).divide(new BigDecimal(divide), 2, BigDecimal.ROUND_HALF_UP);
	}
	/**
	 * 相除
	 * @param divided
	 * @param divide
	 * @return
	 * modify by xuchaoying
	 * 2013-04-27
	 * 修改相除时精度为2，且四舍五入
	 */
	public static BigDecimal divide(BigDecimal divided, BigDecimal divide) {
		return divided.divide(divide, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 相除（下舍处理，精度保留2位）
	 * @param divided
	 * @param divide
	 * @return
	 */
	public static BigDecimal divideFloorWithTwo(BigDecimal divided, BigDecimal divide) {
		return divided.divide(divide, 2, BigDecimal.ROUND_FLOOR);
	}
	
	/**
	 * 相除
	 * @param divided
	 * @param divide
	 * @return
	 * 修改相除时精度为4，不四舍五入，向下取
	 */
	public static BigDecimal divideFloor(BigDecimal divided, BigDecimal divide) {
		return divided.divide(divide, 4, BigDecimal.ROUND_FLOOR);
	}
	
	/**
	 * 相除
	 * @param divided
	 * @param divide
	 * @return
	 * 修改相除时精度为1，不四舍五入，向下取
	 */
	public static BigDecimal divideFloorWithOne(BigDecimal divided, BigDecimal divide) {
		return divided.divide(divide, 1, BigDecimal.ROUND_FLOOR);
	}

	/**
	 * 精确对比两个数字
	 * @param b1 需要被对比的第一个数
	 * @param b2 需要被对比的第二个数
	 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
	 */
	public static int compareTo(BigDecimal b1,BigDecimal b2){
		return b1.compareTo(b2);
	}

	/**
	 * 判断是否为100的整数倍
	 * @param num
	 * @return
	 */
	public static boolean isHundred(BigDecimal num) {
		if(new BigDecimal("0").compareTo(num) >= 0){
			return false;
		}
		
		double doubleValue = num.doubleValue();
		int result = new BigDecimal(doubleValue % 100).compareTo(new BigDecimal("0"));
		
		if(result > 0){
			return false;
		} else {
			return true;
		}
	}
}
