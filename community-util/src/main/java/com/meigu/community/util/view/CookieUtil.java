package com.meigu.community.util.view;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * 设置cookie（接口方法）
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位  
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	public static void  addCookie(HttpServletResponse response,Cookie cookie){
		response.addCookie(cookie);
	}
	public static void  addCookie(HttpServletResponse response,String name,String value){
		Cookie cookie=new Cookie(name,value);
		cookie.setPath("/");
		addCookie(response,cookie);
	}
	
	/**删除Cookie*/
	public static void  removeCookie(HttpServletResponse response,String name) {
		Cookie cookie=new Cookie(name,null);
		cookie.setMaxAge(0);
		addCookie(response,cookie);
	}
	
	/**删除所有Cookie*/
	public  static void  removeAllCookie(HttpServletResponse response,HttpServletRequest request) {
		try {
			Cookie cks[]=request.getCookies();
			for (Cookie cookie : cks) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				addCookie(response,cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
