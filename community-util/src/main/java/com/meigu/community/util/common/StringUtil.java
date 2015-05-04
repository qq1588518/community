package com.meigu.community.util.common;

import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.*;

@SuppressWarnings("deprecation")
public class StringUtil {

	public static boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	} 
	/**是否是(包含)中文汉字*/
	public static Boolean  isChinese(String value){
		Boolean flag=false;
		try{
			if (isNotEmpty(value)) {
				if(value.length()>0){
					Pattern pattern=Pattern.compile("[\\u4e00-\\u9fa5\\uF900-\\uFA2D]+");
					Matcher matcher=pattern.matcher(String.valueOf(value));
					flag=matcher.find();
				}else{
					flag=isChinese(value.charAt(0));
				}
			}else{
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/**是否是中文汉字*/
	public static Boolean  isChinese(char value){
		Boolean flag=false;
		try{
			int num=(int)value;
			if(num>=19968 && num<=171941){
				flag=true;
			}else{
				flag=false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @param value 需要编码的字符串
	 * @return 		编码成URL字符串(like[中国:%E4%B8%AD%E5%9B%BD])
	 */
	public static String encodeText(String value) {
		value=encodeText(value,"UTF-8");
		return value;
	}

	/**
	 * @param value	需要解码的字符串
	 * @return 		解码成正常字符串(like[%E4%B8%AD%E5%9B%BD:中国])
	 */
	public static String decodeText(String value) {
		value=decodeText(value,"UTF-8");
		return value;
	}

	/***
	 * @param value 需要编码的字符串
	 * @param charset	编码格式(like[UTF-8,GBK,GB2312,ISO8859-1 ......])
	 * @return 编码后的字符串
	 */
	public static String encodeText(String value,String charset){
		try {
			if (isNotEmpty(value)) {
				if(isNotEmpty(charset)){
					value=URLEncoder.encode(value,charset);
				}else{
					value=URLEncoder.encode(value,"UTF-8");
				}
			}
		} catch (Exception e) {
			if (isNotEmpty(value)) {
				value=URLEncoder.encode(value);
			}
		}
		return value;
	}

	/**
	 * @param value		需要解码的字符串
	 * @param charset	解码格式(like[UTF-8,GBK,GB2312,ISO8859-1 ......])
	 * @return 			解码后的字符串
	 */
	public static String decodeText(String value,String charset){
		try {
			if (isNotEmpty(value)) {
				if(isNotEmpty(charset)){
					value=URLDecoder.decode(value,charset);
				}else{
					value=URLDecoder.decode(value,"UTF-8");
				}
			}
		} catch (Exception e) {
			if (isNotEmpty(value)) {
				value=URLDecoder.decode(value);
			}
		}
		return value;
	}

	
	public static boolean isNull(String value) {
		if (value==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isBlank(String value) {
		if (value==null) {
			return false;
		}else {
			if (value.trim().length()==0) {
				return true;
			}else {
				return false;
			}
		}
	}
	public static boolean isEmpty(String value) {
		if (isNull(value)||isBlank(value)) {
			return true;
		}else {
			if (isNotNull(value)&&isNotBlank(value)) {
				return false;
			}else {
				return true;
			}
		}
	}
	
	/**
	 * @param value判断的目标字符串
	 * @return 字符串是否为空对象
	 *<pre>
	 *String text		return false
	 *String text=null		return false
	 *String text=""		return true
	 *String text=" "		return true
	 *String text=" abc "	return true
	 *</pre>
	 */
	public static boolean isNotNull(String value) {
		if(value==null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @param value判断的目标字符串
	 * @return 字符串是否为空白
	 *<pre>
	 *String text=null		return false
	 *String text=""		return true
	 *String text=" "		return true
	 *String text=" abc "	return false
	 *</pre>
	 */
	public static boolean isNotBlank(String value) {
		if(isNotNull(value)){
			if(value.trim().equals("")){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}

	/**
	 * @param value判断的目标字符串
	 * @return 目标字符串不为null对象并且不为空白字符串
	 *<pre>
	 * String text=null		return false
	 * String text=""		return false
	 * String text=" "		return false
	 * String text=" abc "	return true
	 *</pre>
	 */
	public static boolean isNotEmpty(String value) {
		if(isNotNull(value)&&isNotBlank(value)){
			return true;
		}else{
			return false;
		}
	}

	/**unicode码转中文字符*/
	public static String  unicodeToZH(String value){
		Pattern  pattern=Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher  matcher=pattern.matcher(value);
		try{
			char ch;
			while(matcher.find()){
				ch=(char)Integer.parseInt(matcher.group(2),16);
				value=value.replace(matcher.group(1),String.valueOf(ch));
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return value;
	}

	/**中文字符转unicode码*/
	public static String  zhToUnicode(String value){
		String text="";
		try{
			if(isNotEmpty(value)){
				String ch;
				for(int i=0;i<value.length();i++){
					if(isChinese(value.charAt(i))){
						ch=Integer.toHexString(value.charAt(i)&0xffff);
						text+="\\u"+ch;
					}else {
						text+=value.charAt(i);
					}
				}
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return text;
	}
	/**
	 * MD5 加密 
	 * @param plainText
	 * @return
	 */
	public static String getMd5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	
}
