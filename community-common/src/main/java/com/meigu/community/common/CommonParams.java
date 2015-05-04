package com.meigu.community.common;

import java.util.*;
import java.io.*;

/**定义公共常用变量*/
public class CommonParams {
	
	private	  static	Properties		params = new Properties();
	public static  String UPLOAD_ROOT_PATH;
	static{
		try {
			InputStream  is=CommonParams.class.getClassLoader().getResourceAsStream("/config.properties");
			params.load(is);
			UPLOAD_ROOT_PATH=params.getProperty("UPLOAD_ROOT_PATH");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**获取设置的常用属性值*/
	public Object getValue(String key) {
		Object  value = null;
		try {
			value = params.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**获取设置的常用属性值*/
	public static String getProperty(String key) {
		String value = null;
		try {
			value = params.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
