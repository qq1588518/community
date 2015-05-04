package com.meigu.community.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.meigu.community.common.CommonParams;
import com.meigu.community.common.ConstantParams;
import com.meigu.community.db.pojo.Notice;
import com.meigu.community.db.pojo.User;
import com.meigu.community.util.common.DateUtil;
import com.meigu.community.util.common.FileUtil;
import com.meigu.community.util.common.StringUtil;

@Controller
@RequestMapping("/mobile/notice")
@SuppressWarnings("unused")
public class MobileNoticeController extends BaseController {

	@RequestMapping(value = "/toSave")
		public String toSave(){
			return "mobile/notice/addNotice";
		}
	
	
	@RequestMapping(value = "/saveNotice")
	public String saveNotice(@ModelAttribute Notice notice, Model model,
			MultipartFile file) {
		try {
			if (notice != null) {
				User user = this.getSessionUser();
				notice.setUser(user);
				notice.setStatus(0);
				notice.setCreateTime(System.currentTimeMillis());
				if (!file.isEmpty()) {
					int lastIndex = file.getOriginalFilename().lastIndexOf(".");
					String suffixName = file.getOriginalFilename().substring(
							lastIndex);
					String fileName = System.currentTimeMillis() + suffixName;
					String path = CommonParams
							.getProperty(ConstantParams.UPLOAD_ACTIVITIES_IMG_LOCALDIR);
					FileUtil.springMvcUpload(file, path, fileName);
					String VirtualPath = CommonParams
							.getProperty(ConstantParams.UPLOAD_ACTIVITIES_IMG_VIRTUALDIR)
							+ "/" + fileName;
					notice.setImg(VirtualPath);
				}
				 boolean b = noticeService.saveNotice(notice);
				 if(b){
				 return "redirect:/mobile/notice/listNotice";
				 }else{
					 return "redirect:/mobile/notice/listNotice";
				 }
			} else {
				 return "redirect:/mobile/notice/listNotice";
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return "redirect:/service/notice/listNotice";
		}
	}

	@RequestMapping(value = "/getNotice/{id}")
	public String getNotice(@PathVariable String id, Model model) {
		try {
			Notice notice = this.noticeService.getNotice(id);
			model.addAttribute("notice", notice);
			return "mobile/notice/getNotice";
		} catch (Exception e) {
			e.printStackTrace();
			return "mobile/notice/getNotice";
		}

	}

	@RequestMapping(value = "/removeNotice/{id}")
	public String removeNotice(@PathVariable String id, Model model) {
		Notice notice = new Notice();
		notice.setId(id);
		notice.setStatus(2);
		try {
			boolean b = this.noticeService.modifyNotice(notice);
			if (b) {
				 return "redirect:/mobile/notice/listNotice";
			} else {
				 return "redirect:/mobile/notice/listNotice";
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return "redirect:/mobile/notice/listNotice";
		}
	}
	
	@RequestMapping(value="/modifyNotice/{id}")
	public String modifyNotice(@PathVariable String id, Model model,Notice notice,MultipartFile file){
		try {
			notice.setId(id);
			if (!file.isEmpty()) {
				String s = file.getOriginalFilename();
				int lastIndex = file.getOriginalFilename().lastIndexOf(".");
				String suffixName = file.getOriginalFilename().substring(
						lastIndex);
				String fileName = System.currentTimeMillis() + suffixName;
				String path = CommonParams
						.getProperty(ConstantParams.UPLOAD_ACTIVITIES_IMG_LOCALDIR);
				FileUtil.springMvcUpload(file, path, fileName);
				String VirtualPath = CommonParams
						.getProperty(ConstantParams.UPLOAD_ACTIVITIES_IMG_VIRTUALDIR)
						+ "/" + fileName;
				notice.setImg(VirtualPath);
			}
			boolean b = this.noticeService.modifyNotice(notice);
			if(b){
				 return "redirect:/mobile/notice/listNotice";
			}else{
				 return "redirect:/mobile/notice/listNotice";
			}
		} catch (Exception e) {
			e.printStackTrace();
			 return "redirect:/mobile/notice/listNotice";
		}
	}

	
	@RequestMapping(value = "/listNotice")
	public String listNotice(String userId, String value, Integer status,String startDate,String endDate,
			Integer currentPage, Integer pageSize, Model model) {
		currentPage	=(currentPage		==null?1:currentPage);
		pageSize=(pageSize	==null?10:pageSize); 
		Long startTime = null;
		Long endTime = null;
		if(!StringUtil.isEmpty(startDate)){
			startTime = DateUtil.parseDate(startDate).getTime();
			model.addAttribute("startDate", startDate);
		}
		if(!StringUtil.isEmpty(endDate)){
			endTime = DateUtil.parseDate(endDate).getTime();
			model.addAttribute("endDate", endDate);
		}
		try {
			page = this.noticeService.listNotices(value, status, userId,startTime,endTime,
					currentPage, pageSize);
			model.addAttribute("page", page);
			model.addAttribute("value", value);
			return "mobile/notice/listNotice";
		} catch (Exception e) {
			e.printStackTrace();
			return "mobile/notice/listNotice";
		}
	}

}
