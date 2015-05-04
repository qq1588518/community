package com.meigu.community.view.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.meigu.community.common.CommonParams;
import com.meigu.community.common.ConstantParams;
import com.meigu.community.db.pojo.AdditionalFile;
import com.meigu.community.db.pojo.FlowCommit;
import com.meigu.community.db.pojo.FlowParameter;
import com.meigu.community.db.pojo.User;
import com.meigu.community.util.common.FileUtil;

@Controller  
@RequestMapping("/mobile/flow")
@SuppressWarnings({})
public class FlowController extends BaseController{

	private Integer pageSize=10;
	
	@RequestMapping(value = "/list/mycategory/{mainId}")  
	public String listMyCategory(@PathVariable Integer mainId,Integer currentPage,HttpSession session,Model model){
		User u=getSessionUser();
		try {
			page=flowService.listMyCategoryFlow(mainId, currentPage, pageSize, u);
			model.addAttribute("mainId", mainId);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/flow/flowlist";
	}
	
	@RequestMapping(value = "/toadd/{mainId}")  
	public String toAddFlow(@PathVariable Integer mainId,HttpSession session,Model model){
		User user=getSessionUser();
		try {
			List<FlowParameter> params =flowService.getFlowParamByFlowId(mainId);
			model.addAttribute("params", params);
			model.addAttribute("mainId", mainId);
			model.addAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/flow/add";
	}
	
	@RequestMapping(value = "/add",method = {RequestMethod.POST })  
	@ResponseBody
	public Map<String,String> addFlow(@RequestBody FlowCommit datas){
		Map<String,String> res=new HashMap<String,String>();
		try {
			String commitId=flowService.addFlowCommit(datas,getSessionUser());
			res.put("state", "success");
			res.put("commitId", commitId);
		} catch (Exception e) {
			res.put("state", "fail");
			e.printStackTrace();
		}
		
		return res;
	}
	@RequestMapping(value = "/detail/{mainId}/{flowCommitId}")  
	public String getFlowDetail(@PathVariable Integer mainId,@PathVariable String flowCommitId,Model model){
		try {
			model.addAttribute("page", this.flowService.listFlowCommitFiles(flowCommitId, null, null));
			model.addAttribute("flowCommit", flowService.getFlowCommitById(flowCommitId));
			model.addAttribute("mainId",mainId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/flow/detail";
	}
	
	@RequestMapping(value = "/delete/{id}")  
	@ResponseBody
	public Map<String,String> removeFlowCommit(@PathVariable String id){
		Map<String,String> res=new HashMap<String,String>();
		try {
			flowService.deleteFlowCommitById(id);
			res.put("state", "success");
		} catch (Exception e) {
			res.put("state", "fail");
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value = "/upload",method = {RequestMethod.POST })  
	public String upload(@RequestParam MultipartFile[] myfiles,String id){
		List<AdditionalFile> list = new ArrayList<AdditionalFile>();
		try {
			if (myfiles.length != 0) {
				for(int i =0;i<myfiles.length;i++){
					AdditionalFile additionalFile = new AdditionalFile();
					FlowCommit flowCommit = new FlowCommit();
							int lastIndex = myfiles[i].getOriginalFilename().lastIndexOf(".");
					String suffixName = myfiles[i].getOriginalFilename().substring(
							lastIndex);
					String fileName = System.currentTimeMillis() + suffixName;
					String path = CommonParams
							.getProperty(ConstantParams.UPLOAD_ANNEX_IMG_LOCALDIR);
					FileUtil.springMvcUpload(myfiles[i], path, fileName);
					String VirtualPath = CommonParams
							.getProperty(ConstantParams.UPLOAD_ANNEX_IMG_VIRTUALDIR)
							+ "/" + fileName;
					additionalFile.setPath(VirtualPath);
					flowCommit.setId(id);
					additionalFile.setCommit(flowCommit);
					list.add(additionalFile);
				}
				Boolean b = flowService.addFlowCommitFiles(list);
				if(b){
					return "redirect:/mobile/flow/list/mycategory/8";
				}else{
					return "redirect:/mobile/flow/list/mycategory/8";
				}
			}else{
				return "redirect:/mobile/flow/list/mycategory/8";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mobile/flow/list/mycategory/8";
	}
	
}
