package com.meigu.community.view.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.*;
import com.meigu.community.util.view.*;


@Controller
@RequestMapping("/manage")
public class MainController extends BaseController{
	
	
	@RequestMapping(value = "/index")
	public String index(String mid,Model model) {
		User  user = this.getSessionUser();
		if (user!=null) {
			Integer type=user.getType();
			if (type.equals(1)) {
				return "redirect:/manage/user/list";
			}else if (type.equals(3)) {
				return "redirect:/service/flow/list";
			}else{
				return "main/index";
			}
		}else{
			if (StringUtil.isNotEmpty(mid)) {
				model.addAttribute("mid", mid);
			}
			return "main/index";
		}
	}
	
	@RequestMapping(value = "/main")
	public String main(Model model) {
		return "redirect:/manage/user/list";
	}
	
	@RequestMapping(value = "/login")
	public String login(String username,String password,Model model) {
		try {
			if (StringUtil.isNotEmpty(username)&&StringUtil.isNotEmpty(password)) {
				User user=userService.login(username, password);
				Object	o_url = this.getSessionValue("url");
				if (user!=null) {
					String url=null;
					if (o_url!=null) {
						url=String.valueOf(o_url);
					}
					Integer type=user.getType();
					if (type.equals(1)) {		//管理员
						this.putSessionUser(user);
						if (StringUtil.isNotEmpty(url)) {
							return String.format("redirect:%s", url);
						}else{
							return "redirect:/manage/user/list";
						}
					}else if(type.equals(3)){	//客服人员
						this.putSessionUser(user);
						if (StringUtil.isNotEmpty(url)) {
							return String.format("redirect:%s", url);
						}else{
							return "redirect:/service/flow/list";
						}
					}else{
						this.setMessage("用户名或密码不正确!");
						return "redirect:/manage/index";
					}
				}else{
					this.removeSessionUser();
					this.setMessage("用户名或密码不正确!");
					return "redirect:/manage/index";
				}
			}else{
				this.setMessage("用户名和密码不可为空!");
				this.removeSessionUser();
				return "redirect:/manage/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("用户名和密码不可为空!");
			this.removeSessionUser();
			return "redirect:/manage/index";
		}
	}
	@RequestMapping(value = "/logout")
	public String logout() {
		this.removeSessionUser();
		this.getSession().setMaxInactiveInterval(0);
		this.getSession().invalidate();
		CookieUtil.removeAllCookie(response, request);
		return "redirect:/manage/index";
	}
}
