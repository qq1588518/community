package com.meigu.community.util.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.meigu.community.util.common.StringUtil;

/**
 * 流程状态显示标签
 */
public class FlowStatusTag extends SimpleTagSupport{

	private				String			key;
	
	private		static	Properties		params=new Properties();
	
	static{
		try {
			InputStream		is =FlowStatusTag.class.getResourceAsStream("/flowstatus.properties");
			params.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		String value="";
		if (StringUtil.isNotEmpty(key)) {
			value = params.getProperty(key);
			if (StringUtil.isNotEmpty(value)) {
				out.append(value);
			}
		}
		super.doTag();
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
