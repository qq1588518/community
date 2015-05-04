package com.meigu.community.util.common;

import java.net.*;
import java.util.*;
import javax.servlet.http.*;

@SuppressWarnings({"unchecked"})
public class RequestUtil {
	
	/**
	 * 获取访问者真实IP地址
	 * */
	public static String  getUserClientIp(HttpServletRequest request){
		String ip=request.getHeader("x-forwarded-for");
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("Proxy-Client-IP");
		}if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("WL-Proxy-Client-IP");
		}if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=request.getRemoteAddr();
		}if(StringUtil.isNotEmpty(ip)&&(!ip.equalsIgnoreCase("unknown"))){
			if(ip.equalsIgnoreCase("127.0.0.1")||ip.startsWith("0:0:")){
				 try {
					InetAddress net=InetAddress.getLocalHost();
					ip=net.getHostAddress();
					if((StringUtil.isNotEmpty(ip)&&ip.length()>15)||(ip.indexOf(",")!=-1)){
						ip=ip.substring(0,ip.indexOf(","));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ip;
	}
	/**获取请求参数*/
	public static String  getParameter(HttpServletRequest request,String key){
		String value=null;
		try{
			value=request.getParameter(key);
		}catch(Throwable t){
			t.printStackTrace();
		}
		return value;
	}
	
	public static  HashMap<String,Object> getParameterMap(HttpServletRequest request){
		HashMap<String,Object>  map=new HashMap<String,Object>();
		try{
			int len;
			Map<String, String[]>  params=request.getParameterMap();
			for(Map.Entry<String, String[]> entry:params.entrySet()){
				len = entry.getValue().length;
				if(len==1){
					map.put(entry.getKey(),entry.getValue()[0]);
				}else{
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**获取请求来源地址*/
	public static String getReferer(HttpServletRequest request) {
		String referer	=	null;
		try {
			String path	=	request.getContextPath();
			referer		=	request.getHeader("referer");
			if (referer!=null&&referer.indexOf(path)!=-1) {
				referer = referer.substring(referer.indexOf(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return referer;
	}
	
	/**获取浏览器代理*/
	public static String getUserAgent(HttpServletRequest request) {
		String  user_agent = null;
		try {
			user_agent = request.getHeader("user-agent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_agent;
	}
	
	/**获取Cookie*/
	public static Cookie getCookie(HttpServletRequest request,String name){
		Cookie cookie=null;
		try{
			if(StringUtil.isNotEmpty(name)){
				Cookie[] cks=request.getCookies();
				if(cks!=null){
					for(Cookie c:cks){
						if(c.getName().equalsIgnoreCase(name)){
							cookie=c;
							break;
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cookie;
	}
	
	/**获取Cookie值*/
	public static String  getCookieValue(HttpServletRequest request,String key){
		String value=null;
		try{
			Cookie cookie=getCookie(request,key);
			if(cookie!=null){
				value=cookie.getValue();
			}
		}catch(Exception e){e.printStackTrace();}
		return value;
	}
	
	public static  void addRequestAttribute(HttpServletRequest request,String key,Object value){
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
