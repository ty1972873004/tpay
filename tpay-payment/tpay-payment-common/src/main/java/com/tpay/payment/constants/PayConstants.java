package com.tpay.payment.constants;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-09 14:14
 **/
public class PayConstants {

    public static final String SUCCESS = "SUCCESS";

    /**
     * 微信公众号支付
     */
    public final static String PAY_CHANNEL_WX_JSAPI = "WX_JSAPI";

    /**
     * 微信原生扫码支付
     */
    public final static String PAY_CHANNEL_WX_NATIVE = "WX_NATIVE";

    /**
     * 微信APP支付
     */
    public final static String PAY_CHANNEL_WX_APP = "WX_APP";

    /**
     * 微信H5支付
     */
    public final static String PAY_CHANNEL_WX_MWEB = "WX_MWEB";

    /**
     * 支付宝移动支付
     */
    public final static String PAY_CHANNEL_ALIPAY_MOBILE = "ALIPAY_MOBILE";

    /**
     * 支付宝PC支付
     */
    public final static String PAY_CHANNEL_ALIPAY_PC = "ALIPAY_PC";

    /**
     * 支付宝WAP支付
     */
    public final static String PAY_CHANNEL_ALIPAY_WAP = "ALIPAY_WAP";

    /**
     *支付宝当面付之扫码支付
     */
    public final static String PAY_CHANNEL_ALIPAY_QR = "ALIPAY_QR";



    public static  class AliPayConstant{

        /**
         * 交易创建，等待买家付款
         */
        public static final String TRADE_STATUS_WAIT = "WAIT_BUYER_PAY";

        /**
         * 	交易支付成功
         */
        public static final String TRADE_STATUS_SUCCESS = "TRADE_SUCCESS";

        /**
         * 	未付款交易超时关闭，或支付完成后全额退款
         */
        public static final String TRADE_STATUS_CLOSED = "TRADE_CLOSED";

        /**
         * 交易结束，不可退款
         */
        public static final String TRADE_STATUS_FINISHED = "TRADE_FINISHED";

    }

    public static  class WechatPayConstant{

        /**
         * 统一下单接口返回失败
         */
        public static final String RETURN_FAIL = "FAIL";

        /**
         * 统一下单接口返回成功
         */
        public static final String RETURN_SUCCESS = "SUCCESS";

    }

}
