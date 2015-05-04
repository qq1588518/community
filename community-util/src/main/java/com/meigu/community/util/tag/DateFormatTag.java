package com.meigu.community.util.tag;

import java.io.*;
import java.util.*;
import java.text.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import com.meigu.community.util.common.StringUtil;

@SuppressWarnings({"unused"})
/**日期格式化*/
public class DateFormatTag extends SimpleTagSupport{

	private	static final  String default_pattern="yyyy-MM-dd HH:mm:ss";
	private	static final  String date_pattern	="yyyy-MM-dd";
	private	static final  String time_pattern	="HH:mm:ss";
	private	static final  String full_pattern	="yyyy-MM-dd HH:mm:ss SSSS";
	private	static final  String long_pattern	="yyyy-MM-dd HH:mm";
	
	/**需要转换的日期值*/
	private Object  value;
	/**转换格式*/
	private	String  pattern;
	/**原始数据格式(针对字符串类型值)*/
	private	String  type;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		String value=this.parse();
		if (StringUtil.isNotEmpty(value)) {
			out.append(value);
		}
		super.doTag();
	}
	
	
	private String parse() {
		String body=null;
		try{
			if(pattern==null){
				pattern=default_pattern;
			}
			SimpleDateFormat sdf=new SimpleDateFormat(pattern);
			if(value instanceof Date){
				body=sdf.format(value);
			}else if (value instanceof Long) {
				body=sdf.format((Long)value);
			}else if (value instanceof String) {
				if(type!=null){
					SimpleDateFormat tdf=new SimpleDateFormat(type);
					Date temp=tdf.parse(value.toString());
					body=sdf.format(temp);
				}else{
					Long  time =Long.parseLong(value.toString());
					body=sdf.format(time);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return body;
	}
	
	
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
