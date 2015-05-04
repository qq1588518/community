package com.meigu.community.view.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.*;
import com.meigu.community.vo.*;


@Controller
@RequestMapping("/service/flow")
@SuppressWarnings("unchecked")
public class ServiceFlowController extends BaseController {

	@RequestMapping(value = "/list")
	public String list(String keyword,String type,Integer status,
			@RequestParam(value="currentPage",required=false) Integer cpage,
			@RequestParam(value="pageSize",required=false) Integer pageSize,Model model) {
		try {
			List<FlowMain> flowlist=flowService.flowlist();
			model.addAttribute("flowMainList", flowlist);
			if (status==null) {
				if (StringUtil.isEmpty(type)) {
					status =1;
				}
			}
			cpage	=(cpage		==null?1:cpage);
			pageSize=(pageSize	==null?10:pageSize);
			page = flowService.flowBriefList(keyword, type, status, null, cpage, pageSize);
			if (StringUtil.isNotEmpty(keyword)) {
				model.addAttribute("keyword", keyword.trim());
			}
			model.addAttribute("type", type);
			model.addAttribute("status", status);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main/service/flow/list";
	}
	
	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable String id,Model model) {
		try {
			FlowBrief   			brief 		= flowService.getFlowBrief(id);
			if(brief.getFlowType()==8){
				List<AdditionalFile> filesList = (List<AdditionalFile>) flowService.listFlowCommitFiles(id, cpage, pageSize).getData();
				model.addAttribute("filesList", filesList);
			}
			List<FlowKeyValueBrief> paramlist	= flowService.paramlist(brief.getId());
			List<FlowHistoryBrief>  historyList = flowService.historyList(brief.getId());
			
			model.addAttribute("brief", brief);
			model.addAttribute("paramlist", paramlist);
			model.addAttribute("historyList", historyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main/service/flow/detail";
	}
	
	/**流程审核*/
	@RequestMapping(value = "/examine")
	@ResponseBody
	public Map<String, Object> examine(String id,Integer status,String reason) {
		String  state="",message="";
		User	loginUser	=	this.getSessionUser();
		try {
			if (StringUtil.isNotEmpty(id)&&(status!=null&&status>1)) {
				FlowCommit  commit 	=flowService.getCommit(id);
				FlowStatus	fstatus	=flowService.getStatus(status);
				if (commit!=null&&fstatus!=null) {
					Boolean flag=flowService.examineFlow(commit,fstatus, reason, loginUser);
					if (flag.equals(true)) {
						state	="success";
						message	="操作成功!";
					}else{
						state	="error";
						message	="操作失败!";
					}
				}else{
					state	="error";
					message	="操作失败!";
				}
			}else{
				state	="error";
				message	="参数异常!";
			}
		} catch (Throwable e) {
			e.printStackTrace();
			state	="error";
			message	="操作失败!";
		}
		root.put("state", state);
		root.put("message", message);
		return root;
	}
}
