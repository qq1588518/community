package com.meigu.community.util.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.meigu.community.util.common.StringUtil;

/**抽奖活动频率*/
public class LotteryRateTag  extends SimpleTagSupport{

private		static	Properties		params=new Properties();

	private				String			key;

	static{
		try {
			InputStream		is =FlowStatusTag.class.getResourceAsStream("/lottery_rate.properties");
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
