package com.tpay.common.utils.tag;

import com.tpay.common.utils.DecimalFormatUtils;

import java.math.BigDecimal;

import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @desc 金额标签
 * @author Trazen
 * @since 2018-04-17
 * @version 1.0
 */
public class MoneyTag extends TagSupport {

	private static final long serialVersionUID = 4443246750743743493L;
	
	private static Logger logger = LoggerFactory.getLogger(MoneyTag.class);
	
	/**
	 * 金额
	 */
	private BigDecimal money;


	public void setMoney(BigDecimal money) {
		this.money = money;
	}


	@Override
	public int doStartTag() {
		
		try {  
			if(money == null) {
				this.pageContext.getOut().println();
			} else {
				this.pageContext.getOut().println(DecimalFormatUtils.format((money).doubleValue(), DecimalFormatUtils.THOUSAND_PATTERN));
			}
        } catch (Exception e) {  
        	logger.error("金额标签渲染失败", e);
        }  
		
		return 0;
	}
	
}
