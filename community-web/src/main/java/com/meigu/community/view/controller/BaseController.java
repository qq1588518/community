package com.meigu.community.view.controller;

import java.util.*;
import org.slf4j.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.AbstractController;
import com.meigu.community.common.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.service.*;
import com.meigu.community.util.common.*;
import com.meigu.community.util.persistence.model.Paginate;

@SuppressWarnings({"unused","unchecked"})
@Controller
@Scope("session")
public class BaseController extends AbstractController{

	protected 	Logger 					logger 	= LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected 	HttpServletRequest 		request;
	
	protected	HttpServletResponse		response;
	
	@Autowired
	protected	HttpSession				session;
	@Autowired
	protected	UserService				userService;
	@Autowired
	protected	HouseService			houseService;
	@Autowired
	protected 	FlowService 			flowService;
	@Autowired
	protected 	NoticeService 			noticeService;
	@Autowired
	protected   ActivitiesService       activitiesService;
	@Autowired
	protected   UserActivitiesRelationService       userActivitiesRelationService;
	@Autowired
	protected	LotteryService			lotteryService;
	
	
	/**存储返回json串的变量*/
	protected 	Map<String, Object>     root	=	new HashMap<String, Object>();
	
	protected	Integer					cpage	=	1;
	protected	Integer					pageSize=	10;
	protected	Paginate				page	=	new Paginate();
	
	protected		String					path;
	protected		String					basePath;
	protected		String					message;
	protected		String					timestamp;
	
	/**获取当前登录用户*/
	protected User getSessionUser() {
		User  user =null;
		try {
			HttpSession  session = request.getSession();
			Object value = session.getAttribute(ConstantParams.SESSION_USER_KEY);
			if (value!=null) {
				user =(User) value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**加入当前会话用户*/
	protected void putSessionUser(User  user) {
		HttpSession  session = request.getSession();
		session.setAttribute(ConstantParams.SESSION_USER_KEY, user);
	}
	
	/**移除会话(session)用户*/
	protected void	removeSessionUser(){
		HttpSession  session = request.getSession();
		session.removeAttribute(ConstantParams.SESSION_USER_KEY);
	}
	
	protected Object getSessionValue(String key) {
		HttpSession  session = request.getSession();
		return session.getAttribute(key);
	}
	
	protected void putSessionValue(String key,Object value) {
		HttpSession  session = request.getSession();
		session.setAttribute(key, value);
	}
	
	protected	Object	getRequestValue(String key){
		return  request.getAttribute(key);
	}

	protected	void	putRequestValue(String key,Object value){
		request.setAttribute(key, value);
	}
	
	/**向会话session中存入消息*/
	protected void putMessage(String key,Object value) {
		HttpSession  session = request.getSession();
		Object  temp=session.getAttribute(ConstantParams.SESSION_MESSAGE_KEY);
		if (temp!=null) {
			Map<String, Object> message	=	(Map<String, Object>) temp;
			message.put(key, value);
			this.putSessionValue(ConstantParams.SESSION_MESSAGE_KEY, message);
		}else{
			Map<String, Object> message	=	new HashMap<String, Object>();
			message.put(key, value);
			this.putSessionValue(ConstantParams.SESSION_MESSAGE_KEY, message);
		}
	}
	
	/**项目路径*/
	public String getPath() {
		this.path = request.getContextPath();
		return path;
	}

	
	public String getBasePath() {
		String	path	=	this.getRequest().getContextPath();
		String	scheme	=	this.getRequest().getScheme();
		String	server	=	this.getRequest().getServerName();
		Integer port	=	this.getRequest().getServerPort();
		if (port.equals(80)) {
			if (StringUtil.isNotEmpty(path)) {
				basePath = String.format("%s://%s%s/",scheme,server,path);
			}else {
				basePath = String.format("%s://%s/",scheme,server);
			}
		}else {
			if (StringUtil.isNotEmpty(path)) {
				basePath = String.format("%s://%s:%d%s/",scheme,server,port,path);
			}else {
				basePath = String.format("%s://%s:%d/",scheme,server,port);
			}
		}
		return basePath;
	}

	
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return session;
	}
	
	private void setSession(HttpSession session) {
		this.session = session;
	}
	

	public Map<String, Object> getRoot() {
		return root;
	}

	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getTimestamp() {
		timestamp=String.valueOf(System.currentTimeMillis());
		return timestamp;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return super.handleRequest(request, response);
	}

	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
