package com.tpay.payment.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @desc 
 * @author Trazen
 * @since 2018-04-11
 * @version 1.0
 */
public class Configure {



	//以下是几个API的路径：
	/**
	 * 被扫支付API
	 */
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 被扫支付查询API
	 */
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 退款API
	 */
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 退款查询API
	 */
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	/**
	 * 撤销API
	 */
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	/**
	 * 下载对账单API
	 */
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	/**
	 * 统计上报API
	 */
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	/**
	 * 临时票据API
	 */
	public static String TICKET_API = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	/**
	 * access_token API
	 */
	public static String TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * 微信OPENID API
	 */
	public static String OPENID_API = "https://api.weixin.qq.com/sns/oauth2/access_token";


	private static String token = null;
	private static Date tokenTime = null;
	private static String jsapiTicket = null;
	private static Date jsapiTicketTime = null;

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		Configure.token = token;
		Configure.tokenTime = new Date();
	}

	public static String getJsapiTicket() {
		return jsapiTicket;
	}

	public static void setJsapiTicket(String jsapiTicket) {
		Configure.jsapiTicket = jsapiTicket;
		Configure.jsapiTicketTime = new Date();
	}

	public static boolean checkToken() {
		if (!StringUtils.isEmpty(Configure.token)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tokenTime);
			calendar.add(Calendar.SECOND, 7200);
			return calendar.before(new Date());
		}
		return true;
	}

	public static boolean checkJsapiTicket() {
		if (!StringUtils.isEmpty(Configure.jsapiTicket)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(jsapiTicketTime);
			calendar.add(Calendar.SECOND, 7200);
			return calendar.before(new Date());
		}
		return true;
	}

}
