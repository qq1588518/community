package com.meigu.community.view.controller;

import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.meigu.community.common.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.*;
import com.meigu.community.util.view.*;


@Controller
@RequestMapping("/mobile")
public class MobileController extends BaseController{
	
	
	@RequestMapping(value = "toOwnerLogin")
	public String toOwnerLogin(){
		return "mobile/login";
	}
	
	
	/**
	 * 业主登录
	 * @param username
	 * @param password
	 * @param model
	 */
	@RequestMapping(value = "/ownerLogin")
	public  String ownerLogin(String username,String password,Model model,HttpSession session, HttpServletResponse response,
			HttpServletRequest req) {
		try {
			if (StringUtil.isNotEmpty(username)&&StringUtil.isNotEmpty(password)) {
				User user=userService.login(username, password);
				if (user!=null && user.getType()==2) {
					this.putSessionUser(user);
					session.setAttribute("user", user);
					CookieUtil.addCookie(response,ConstantParams.COMMON_COOKIE_USERNAME,StringUtil.encodeText(user.getUsername()), 60 * 60 * 24 * 365 * 5);
					CookieUtil.addCookie(response,ConstantParams.COMMON_COOKIE_PASSWORD,StringUtil.encodeText(user.getPassword()), 60 * 60 * 24 * 365 * 5);
					String url =  (String)this.getSessionValue("url");
					if(url == null ){
						return "redirect:/mobile/owner/modify";
					}else{
						this.getSession().removeAttribute("url");
						return "redirect:"+url;
					}
				}else{
					this.removeSessionUser();
					this.setMessage("用户名或密码不正确!");
					return "redirect:/mobile/toOwnerLogin";
				}
			}else{
				this.setMessage("用户名和密码不可为空!");
				this.removeSessionUser();
				return "redirect:/mobile/toOwnerLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("用户名和密码不可为空!");
			this.removeSessionUser();
			return "redirect:/mobile/toOwnerLogin";
		}
	}
	

	@RequestMapping(value = "/main")
	public String main() {
		return"mobile/main";
	}
	
	@RequestMapping(value = "/toOwnerRegister")
	public String toOwnerRegister() {
			return"mobile/regist";
	}
	
	/**  注册业主
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ownerRegister")
	public String ownerRegister(@ModelAttribute User user,@ModelAttribute House house,Model model) {
		if(user != null){
			try{
				user.setType(2);
				user.setStatus(0);
				this.userService.save(user);
				if(!StringUtil.isEmpty(house.getBuildNo()) && !StringUtil.isEmpty(house.getUnitNo()) && !StringUtil.isEmpty(house.getHouseName())){
					house.setUser(user);
					houseService.saveHouse(house);
				}
				return "redirect:/mobile/toOwnerLogin";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/mobile/ownerRegister";
			}
		}else{
			return "redirect:/mobile/ownerRegister";
		}
	}
	
	/**跳转到修改*/
	@RequestMapping(value = "/owner/modify")
	public String modify(Model model) {
		try{
			User  user =this.getSessionUser();
			model.addAttribute("user", user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/modify";
	}
	
	/**提交修改*/
	@RequestMapping(value = "/owner/update")
	public String modify(@ModelAttribute User user) {
		try {
			Boolean flag=userService.updateUser(user);
			if (flag) {
				user =userService.getUser(user.getId());
				this.putSessionUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mobile/owner/modify";
	}
	
	
	@RequestMapping(value = "/checkOwnerUser")
	public @ResponseBody String checkOwnerUser(String username, Model model,HttpServletRequest request) {
		String msg = "";
		try {
			User user = this.userService.checkOwnerUser(username);
			if(user == null){
				msg = "true";
			}else{
				msg = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		this.removeSessionUser();
		this.getSession().setMaxInactiveInterval(0);
		this.getSession().invalidate();
		CookieUtil.removeAllCookie(response, request);
		return "redirect:/mobile/toOwnerLogin";
	}
	
}
