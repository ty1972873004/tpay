package com.tpay.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/** 
 * 数字格式化工具类
 * @author xuchaoying 
 * @since 2015-4-14
 * @version 1.0 
 */
public class DecimalFormatUtils {

	private static final String DEFAULT_PATTERN = "#.00";
	public static final String THOUSAND_PATTERN = "###,###.00";
	
	public static final String THOUSAND_INTEGER_PATTERN = "###,###";
	
	/**
	 * 格式化金额
	 * @param money
	 * @return
	 */
	public static String format(Double money) {
		if(money.compareTo(Double.valueOf("0")) == 0) {
			return "0.00";
		}
		if(money.compareTo(Double.valueOf("0")) > 0 && money.compareTo(Double.valueOf("1")) < 0) {
			return "0" + new DecimalFormat(DEFAULT_PATTERN).format(money);
		}
		if(money.compareTo(Double.valueOf("-1")) > 0 && money.compareTo(Double.valueOf("0")) < 0) {
			Double newMoney = -money;
			return "-0" + new DecimalFormat(DEFAULT_PATTERN).format(newMoney);
		}
		return new DecimalFormat(DEFAULT_PATTERN).format(money);
	}
	
	/**
	 * 格式化金额
	 * @param money
	 * @return
	 */
	public static String format(BigDecimal money) {
		if(money.compareTo(BigDecimal.ZERO) == 0) {
			return "0.00";
		}
		if(money.compareTo(BigDecimal.ZERO) > 0 && money.compareTo(new BigDecimal(1)) < 0) {
			return "0" + new DecimalFormat(DEFAULT_PATTERN).format(money);
		}
		if(money.compareTo(new BigDecimal(-1)) > 0 && money.compareTo(BigDecimal.ZERO) < 0) {
			BigDecimal newMoney =  BigDecimalUtils.multiply(money, new BigDecimal(-1) );
			return "-0" + new DecimalFormat(DEFAULT_PATTERN).format(newMoney);
		}
		return new DecimalFormat(DEFAULT_PATTERN).format(money);
	}
	/**
	 * 格式化金额
	 * @param money
	 * @return
	 */
	public static String format(Double money, String pattern) {
		if(money.compareTo(Double.valueOf("0")) == 0) {
			return "0.00";
		}
		if(money.compareTo(Double.valueOf("0")) > 0 && money.compareTo(Double.valueOf("1")) < 0) {
			return "0" + new DecimalFormat(DEFAULT_PATTERN).format(money);
		}
		if(money.compareTo(Double.valueOf("-1")) > 0 && money.compareTo(Double.valueOf("0")) < 0) {
			Double newMoney = -money;
			return "-0" + new DecimalFormat(DEFAULT_PATTERN).format(newMoney);
		}
		return new DecimalFormat(pattern).format(money);
	}
	
	/**
	 * 格式化整形金额
	 * @param money
	 * @param pattern
	 * @return
	 */
	public static String formatInteger(Integer money, String pattern) {
		return new DecimalFormat(pattern).format(money);
	}
	
	public static void main(String[] args) {
		System.out.println(formatInteger(10000, THOUSAND_INTEGER_PATTERN));
	}
}
