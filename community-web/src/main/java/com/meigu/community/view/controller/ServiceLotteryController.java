package com.meigu.community.view.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.*;

/**抽奖活动*/
@Controller
@RequestMapping("/service/lottery")
public class ServiceLotteryController extends BaseController{

	/**抽奖活动列表*/
	@RequestMapping(value = "/list")
	public String list(String keyword,Integer rate,String startDate,String endDate,Integer status,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer cpage,
			@RequestParam(value="pageSize",required=false,defaultValue="10") Integer pageSize,Model model) {
		try {
			Long  startTime = null,endTime = null;
			if (StringUtil.isNotEmpty(keyword)) {
				model.addAttribute("keyword", keyword.trim());
			}
			if (rate!=null) {
				model.addAttribute("rate",rate);
			}
			if (StringUtil.isNotEmpty(startDate)) {
				model.addAttribute("startDate", startDate.trim());
				startTime = DateUtil.parseDate(startDate).getTime();
			}
			if (StringUtil.isNotEmpty(endDate)) {
				model.addAttribute("endDate", endDate.trim());
				endTime = DateUtil.parseDate(endDate).getTime();
			}
			if (status!=null) {
				model.addAttribute("status", status);
			}
			page = lotteryService.lotterylist(keyword, rate, startTime, endTime, status, cpage, pageSize);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		model.addAttribute("page", page);
		return "main/service/lottery/activity_list";
	}
	
	/**中奖奖品列表*/
	@RequestMapping(value = "/list",params="type=award")
	public String list(String keyword,String startDate,String endDate,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer cpage,
			@RequestParam(value="pageSize",required=false,defaultValue="10") Integer pageSize,Model model) {
		try {
			Long  startTime = null,endTime = null;
			if (StringUtil.isNotEmpty(keyword)) {
				model.addAttribute("keyword", keyword.trim());
			}
			if (StringUtil.isNotEmpty(startDate)) {
				model.addAttribute("startDate", startDate.trim());
				startTime = DateUtil.parseDate(startDate).getTime();
			}
			if (StringUtil.isNotEmpty(endDate)) {
				model.addAttribute("endDate", endDate.trim());
				endTime = DateUtil.parseDate(endDate).getTime();
			}
			page	= lotteryService.awardlist(keyword, startTime, endTime, cpage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("page", page);
		return "main/service/lottery/award_list";
	}
	
	/**详情显示*/
	@RequestMapping(value = "/{action}/{id}")
	public String show(@PathVariable String action,@PathVariable Integer id,Model model) {
		try {
			if (StringUtil.isNotEmpty(action)&&id!=null) {
				Lottery  lottery = lotteryService.getLottery(id);
				if (lottery!=null) {
					model.addAttribute("lottery", lottery);
					if (action.equalsIgnoreCase("view")) {
						return "main/service/lottery/detail";
					}else if (action.equals("modify")) {
						Double radix =lottery.getRadix();
						Integer base=((Long)Math.round(1/radix)).intValue();
						model.addAttribute("base", base);
						return "main/service/lottery/modify";
					}else{
						return "redirect:/service/lottery/list";
					}
				}else{
					return "main/service/lottery/detail";
				}
			}else{
				return "main/service/lottery/detail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/service/lottery/list";
		}
	}
	
	/**添加*/
	@RequestMapping(value = "/add")
	public String add(Model model) {
		return "main/service/lottery/add";
	}
	
	/**保存*/
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String,Object> save(@RequestBody Lottery  lottery) {
		String state="success",message="";
		try {
			if (lottery!=null) {
				lotteryService.insertLottery(lottery);
				Integer id = lottery.getId();
				if (id!=null) {
					root.put("data", id);
					state="success";
					message="添加成功!";
				}else{
					state="error";
					message="添加失败!";
				}
			}else{
				state="error";
				message="信息填写不完整!";
			}
		} catch (Exception e) {
			state="error";
			message="操作失败!";
			e.printStackTrace();
		}
		root.put("state", state);
		root.put("message",message);
		return root;
	}
	
	/**修改*/
	@RequestMapping(value = "/modify")
	@ResponseBody
	public  Map<String,Object> modify(@RequestBody Lottery  lottery) {
		String state="success",message="更新成功!";
		Integer		id	= lottery.getId();
		try {
			if (id!=null) {
				Boolean flag=lotteryService.updateLottery(lottery);
				if (flag.equals(true)) {
					id	= lottery.getId();
					state="success";
					message="更新成功!";
					root.put("data", id);
				}else{
					state="error";
					message="更新失败!";
				}
			}else{
				state="error";
				message="数据格式错误!";
			}
		} catch (Exception e) {
			state="error";
			message="更新失败!";
			e.printStackTrace();
		}
		root.put("state", state);
		root.put("message",message);
		return root;
	}
	
}
