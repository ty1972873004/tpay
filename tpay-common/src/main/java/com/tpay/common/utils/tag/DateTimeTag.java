package com.tpay.common.utils.tag;

import com.tpay.common.utils.DateUtils;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @desc 日期时间控件标签
 * @author Trazen
 * @since 2018-04-17
 * @version 1.0
 */
public class DateTimeTag extends TagSupport {

	private static final long serialVersionUID = 4443246750743743493L;
	
	public static final String DEFAULT_DATE_TIME_PATTERN = "yyyyMMddHHmmss";

	public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy.MM.dd HH:mm:ss";

	public static final String DEFAULT_MONTH_TIME_PATTERN = "yyyyMM";

	private static Logger logger = LoggerFactory.getLogger(DateTimeTag.class);

	/**
	 * 日期时间字符串（yyyyMMddHHmmss）
	 */
	private String strDateTime;
	
	/**
	 * 原来格式
	 */
	private String oldType;
	
	/**
	 * 转换格式
	 */
	private String type;
	public String getStrDateTime() {
		return strDateTime;
	}

	public void setStrDateTime(String strDateTime) {
		this.strDateTime = strDateTime;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOldType() {
		return oldType;
	}

	public void setOldType(String oldType) {
		this.oldType = oldType;
	}

	@Override
	public int doStartTag() {
		try {
			if(StringUtils.isEmpty(strDateTime)) {
				this.pageContext.getOut().println();
			} else if(StringUtils.isNotEmpty(type)){
				this.pageContext.getOut().println(DateUtils.format(
						DateUtils.parse(strDateTime, oldType),
						type)); 
			}else if(StringUtils.isNotEmpty(strDateTime)&& StringUtils.isEmpty(type))  {
				this.pageContext.getOut().println(DateUtils.format(
						DateUtils.parse(strDateTime, DEFAULT_DATE_TIME_PATTERN), 
						DEFAULT_DATE_TIME_FORMAT_PATTERN));  
			}
        } catch (Exception e) {  
        	logger.error("日期时间标签渲染失败", e);
        }  
		
		return 0;
	}
	
}
