package com.meigu.community.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/**
 * 字符串截取
 */
public class TextCutTag extends SimpleTagSupport{

	private		String		value				;//页面传入值
	private		String		type		= "text";//传值类型(text;html/xml;)
	private		Integer		start		= null  ;//开始截取位置
	private		Integer		end			= null  ;//结束截取位置
	private		String		search		= null  ;//要搜索的字符串
	private		String		replace		= null  ;//要替换的字符串
	private 	String		symbol		= null  ;//结束符
	private		Integer		residue		= null  ;//剩余字数
	private		Boolean		clean		= true	;//是否清除html样式,图片等...
	
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		try {
			if (type.equalsIgnoreCase("html")||type.equalsIgnoreCase("xml")) {
				if (clean) {
					value=value.replaceAll("<.*?>", "").replaceAll("\\&[a-zA-Z]{1,10};", "");
				}
			}
			if (start!=null&&end!=null) {
				value=value.substring(start, end);
			}else if (start!=null&&end==null&&residue!=null) {
				value=value.substring(start,(start+residue));
			}else if (start==null&&end!=null) {
				value=value.substring(0, end);
			}else if (start==null&&end==null&&residue!=null) {
				if (value.length()>residue) {
					value=value.substring(0, residue);
				}
			}else if (start!=null&&end==null&&residue==null) {
				value=value.substring(start);
			}
			if (search!=null) {
				replace=((replace==null)?"":replace);
				value=value.replaceAll(search, replace);
			}
			if (symbol!=null) {
				value=String.format("%s%s",value,symbol);
			}
			out.append(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.doTag();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getResidue() {
		return residue;
	}

	public void setResidue(Integer residue) {
		this.residue = residue;
	}

	public Boolean getClean() {
		return clean;
	}

	public void setClean(Boolean clean) {
		this.clean = clean;
	}
	
}
