package com.meigu.community.view.interceptor;


import java.util.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.*;
import com.meigu.community.common.ConstantParams;
import com.meigu.community.db.pojo.User;
import com.meigu.community.service.UserService;
import com.meigu.community.util.common.RequestUtil;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.view.controller.BaseController;

@SuppressWarnings("serial")
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private		UserService			userService;
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView view) throws Exception {
		String 	path		=	request.getContextPath();
		Long	timestamp	=	System.currentTimeMillis();
		Date	now			=	new		Date(timestamp);
		request.getSession().setAttribute("path", path);
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("now", now);
	}

	/**放弃验证的请求地址*/
	private	 static final	ArrayList<String>  passurl =new ArrayList<String>(20){{
		add("/");
		add("/login");
		add("/logout");
		add("/check");
		add("/index");
		add("/regist");
		add("/toOwnerLogin");
		add("/ownerLogin");
		add("/checkOwnerUser");
		add("/toOwnerRegister");
		add("/ownerRegister");
	}};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod  hm=(HandlerMethod) handler; 
				BaseController  controller  = (BaseController) hm.getBean();
				controller.setResponse(response);
			}
			Object referer_url=request.getSession().getAttribute("referer");
			if(referer_url!=null){
				String referer_uri=String.valueOf(referer_url);
				String referer =RequestUtil.getReferer(request);
				if (StringUtil.isNotEmpty(referer)) {
					if (referer.equalsIgnoreCase(referer_uri)) {
						request.getSession().setAttribute("referer", referer);
					}else{
						request.getSession().setAttribute("referer", referer);
					}
				}else{
					request.getSession().removeAttribute("referer");
				}
			}else{
				String referer =RequestUtil.getReferer(request);
				if (StringUtil.isNotEmpty(referer)) {
					request.getSession().setAttribute("referer", referer);
				}else{
					request.getSession().removeAttribute("referer");
				}
			}
		} catch (Exception e) {}
		
		String path=request.getContextPath();
		String url		=	request.getServletPath();
		String action	=	url.substring(url.lastIndexOf("/"));
		if(passurl.contains(url)||passurl.contains(action)){
			return true;
		}else{
			Object user=request.getSession().getAttribute(ConstantParams.SESSION_USER_KEY);
			if (user!=null) {
				User	loginUser = (User)user;
				Integer type = loginUser.getType();
				if (url.startsWith("/manage")&&type.equals(1)) {		//管理员
					return true;
				}else if(url.startsWith("/service")&&type.equals(3)){	//客服
					return true;
				}else if(url.startsWith("/mobile")&&type.equals(2)){
					return true;
				}else if (url.startsWith("/mobile")&&(!type.equals(2))) {
					request.getSession().removeAttribute(ConstantParams.SESSION_USER_KEY);
					request.getSession().setAttribute("url", url);
					response.sendRedirect(path+"/mobile/toOwnerLogin");
					return false;
				}else if (type.equals(2)&&(url.startsWith("/manage")||url.startsWith("/service"))) {
					request.getSession().removeAttribute(ConstantParams.SESSION_USER_KEY);
					request.getSession().setAttribute("url", url);
					response.sendRedirect(path+"/manage/index");
					return false;
				}else{
					return true;
				}
			}else{
				if(url.startsWith("/mobile")){
					String   	username=StringUtil.decodeText(RequestUtil.getCookieValue(request, ConstantParams.COMMON_COOKIE_USERNAME));
					String		password=StringUtil.decodeText(RequestUtil.getCookieValue(request, ConstantParams.COMMON_COOKIE_PASSWORD));
					if (StringUtil.isNotEmpty(username)&&StringUtil.isNotEmpty(password)) {
						user =	userService.login(username, password);
						if (user!=null) {
							request.getSession().setAttribute(ConstantParams.SESSION_USER_KEY, user);
							return true;
						}else{
							request.getSession().setAttribute("url", url);
							response.sendRedirect(path+"/mobile/toOwnerLogin");
							return false;
						}
					}else{
						request.getSession().setAttribute("url", url);
						response.sendRedirect(path+"/mobile/toOwnerLogin");
						return false;
					}
				}else{
					request.getSession().setAttribute("url", url);
					response.sendRedirect(path+"/manage/index");
					return false;
				}
			}
		}
	}
	
	
}