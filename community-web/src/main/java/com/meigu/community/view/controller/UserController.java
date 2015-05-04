package com.meigu.community.view.controller;

import java.util.Map;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.meigu.community.db.pojo.User;
import com.meigu.community.util.common.StringUtil;


@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {

	@RequestMapping(value = "/list")
	public String list(String keyword,Integer type,
			@RequestParam(value="currentPage",required=false) Integer cpage,
			@RequestParam(value="pageSize",required=false) Integer pageSize,Model model) {
		type	=(type		==null?1:type);
		cpage	=(cpage		==null?1:cpage);
		pageSize=(pageSize	==null?10:pageSize);
		page=userService.userlist(keyword, type, null, cpage, pageSize);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);
		model.addAttribute("page", page);
		return "main/user/list";
	}
	
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean checkUser(String username) {
		Boolean  flag=false;
		try {
			if (StringUtil.isNotEmpty(username)) {
				User user=userService.checkOwnerUser(username);
				if (user!=null) {
					flag=false;
				}else{
					flag=true;
				}
			}else{
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	@RequestMapping(value = "/{id}")
	public String view(@PathVariable String id,Model model) {
		User user = null;
		try {
			if (StringUtil.isNotEmpty(id)) {
				user = userService.getUser(id);
				Integer  type = user.getType();
				if (type.equals(3)) {
					model.addAttribute("user", user);
					return "main/user/modify";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		return "main/user/view";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) {
		
		return "main/user/add";
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Map<String, Object> remove(String id) {
		try {
			User	loginUser = this.getSessionUser();
			User user =userService.getUser(id);
			if (user!=null) {
				if (loginUser.getId().equals(user.getId())) {
					root.put("state","error");
					root.put("message","禁止删除自己!");
				}else{
					Integer type=user.getType();
					if (type.equals(3)) {
						Boolean		flag=userService.remove(user);
						if (flag) {
							root.put("state","success");
							root.put("message","删除成功!");
						}else{
							root.put("state","error");
							root.put("message","删除失败!");
						}
					}else{
						root.put("state","error");
						root.put("message","非法删除!");
					}
				}
			}else{
				root.put("state","error");
				root.put("message","未知用户!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			root.put("state","error");
			root.put("message","删除失败!");
		}
		return root;
	}
	
	@RequestMapping(value = "/modify")
	public String modify(@ModelAttribute User user) {
		String	userId=user.getId();
		try {
			Boolean flag=userService.updateUser(user);
			if (flag) {
				this.setMessage("修改成功!");
				return "redirect:/manage/user/list";
			}else{
				this.setMessage("修改失败!");
				return String.format("redirect:/manage/user/%s", userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("修改失败!");
			return String.format("redirect:/manage/user/%s", userId);
		}
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute User user) {
		try {
			if(user!=null){
				user.setType(3);
				user.setStatus(0);
			}
			Boolean flag=userService.saveUser(user);
			if (flag) {
				this.setMessage("保存成功!");
				return "redirect:/manage/user/list";
			}else{
				this.setMessage("保存失败!");
				return "redirect:/manage/user/add";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("保存失败!");
			return "redirect:/manage/user/add";
		}
	}
}
