package com.tpay.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * @author tuyong
 * @version 1.0
 * @desc  金额工具类
 * @create 2018-04-17 9:41
 **/
public class MoneyUtils {

    /**
     * 将元单位的格式,转换为分
     * @param money
     * @return
     */
    public static String toCent(String money) {
        String moneyValue = "0";
        if ((money == null) || (money.equals(""))) {
            return moneyValue;
        }
        money = money.trim();
        money = money.replaceAll(",", "");
        BigDecimal bigDecimal = new BigDecimal(money);
        bigDecimal = bigDecimal.setScale(2, 4);// 四舍五入,去2位小数
        money = bigDecimal.toString();
        StringTokenizer st = new StringTokenizer(money, ".");
        moneyValue = st.nextToken() + st.nextToken();
        return moneyValue;
    }

    public static String toCent(Long money) {
        return toCent(money.toString());
    }

    public static String toCent(BigDecimal money) {
        money = money.setScale(2, RoundingMode.HALF_UP);
        StringTokenizer st = new StringTokenizer(money.toString(), ".");
        return st.nextToken() + st.nextToken();
    }

    public static String toCent(Double doubleMoney) {
        BigDecimal money = new BigDecimal(doubleMoney.doubleValue());
        money = money.setScale(2, RoundingMode.HALF_UP);
        StringTokenizer st = new StringTokenizer(money.toString(), ".");
        return st.nextToken() + st.nextToken();
    }

    public static String toDollar(String money) {
        BigDecimal bigDecimal = new BigDecimal(money);
        bigDecimal = bigDecimal.movePointLeft(2);
        return bigDecimal.toString();
    }

    public static String toDollar(Long money) {
        return toDollar(money.toString());
    }

    public static String toDollar(Integer txnFee) {
        return toDollar(txnFee.toString());
    }

    public static double round(double v, int scale) {
        if ((Double.isInfinite(v)) || (Double.isNaN(v))) {
            v = 0.0D;
        }
        if (scale < 0) {
            scale = 0;
        }
        String vStr = Double.toString(v);
        BigDecimal b = new BigDecimal(vStr);
        BigDecimal one = new BigDecimal("1");

        double result = b.divide(one, scale, 4).doubleValue();
        return result;
    }

    public static int compareMoney(String money1, String money2) {
        BigDecimal bigDecimal = new BigDecimal(money1);
        BigDecimal bigDecima2 = new BigDecimal(money2);
        return bigDecimal.compareTo(bigDecima2);
    }

    public static String formatMoney(String money) {
        BigDecimal bigDecimal = new BigDecimal(money);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.toString();
    }

    public static String trimPrefixZeroMoney(String money) {
        BigDecimal bigDecimal = new BigDecimal(money);
        return bigDecimal.toString();
    }

    /**
     * 百分比计算
     * @param money
     * @param rate
     * @return
     * @author zcguo 2012-01-22
     */
    public static Long multiPercentage(String money, String rate){
        BigInteger biMoney = new BigInteger(money);
        BigInteger biRate = new BigInteger(rate);
        BigInteger result = biMoney.multiply(biRate).divide(new BigInteger("100"));
        return result.longValue();
    }

}
