package com.meigu.community.view.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.meigu.community.common.CommonParams;
import com.meigu.community.common.ConstantParams;
import com.meigu.community.db.pojo.Activities;
import com.meigu.community.db.pojo.User;
import com.meigu.community.db.pojo.UserActivitiesRelation;
import com.meigu.community.util.common.DateUtil;
import com.meigu.community.util.common.FileUtil;
import com.meigu.community.util.common.StringUtil;

@Controller
@RequestMapping("/mobile/activities")
@SuppressWarnings({"unchecked"})
public class MobileActivitiesController extends BaseController {

	@RequestMapping(value = "/toSave")
	public String toSave() {
		return "mobile/notice/addActivities";
	}

	@RequestMapping(value = "/save")
	public String saveActivities(@ModelAttribute Activities activities, Model model ,MultipartFile file) {

		try {
			User user = this.getSessionUser();
			activities.setUser(user);
			activities.setUser(user);
			activities.setStatus(0);
			activities.setAlreadyNum(0);
			activities.setHot(0);
			activities.setCreateTime(System.currentTimeMillis());
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
				activities.setImg(VirtualPath);
				model.addAttribute("VirtualPath", VirtualPath);
			}
			Boolean b = this.activitiesService.saveActivities(activities);
			if (b) {
				return "redirect:/mobile/activities/listActivities";
			} else {
				return "redirect:/mobile/activities/listActivities";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/mobile/activities/listActivities";
		}
	}

	@RequestMapping(value = "removeActivities/{id}")
	public String removeActivities(@PathVariable String id, Model model) {
		try {

			Activities activities = new Activities();
			activities.setId(id);
			activities.setStatus(2);
			boolean b = this.activitiesService.modifyActivities(activities);
			if (b) {
				return "redirect:/service/activities/listActivities";
			} else {
				return "redirect:/service/activities/listActivities";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/service/activities/listActivities";

	}

	@RequestMapping(value = "getActivities/{id}")
	public String getActivities(@PathVariable String id, Integer mark,Model model) {
		try {
			Activities activities = this.activitiesService.getActivities(id);
			model.addAttribute("activities", activities);
			model.addAttribute("mark", mark);
			return "mobile/notice/getActivities";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/notice/getActivities";
	}

	@RequestMapping(value = "/listActivities")
	public String listActivities(String value, Integer status, String userId,String startDate,String endDate,
			Integer hot, Integer currentPage, Integer pageSize, Model model) {
		try {
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
			currentPage = (currentPage == null ? 1 : currentPage);
			pageSize = (pageSize == null ? 10 : pageSize);
			if(status != null && status==2)status=null;
			page = this.activitiesService.listActivities(value, status, userId,
					hot,startTime, endTime,currentPage, pageSize);
			List<Activities> list = (List<Activities>) page.getData();
			Integer[] mak =new Integer[list.size()];
			String sessionId = getSessionUser().getId() ;
			for(int i=0;i<list.size();i++){
				mak[i]=0;
				List<UserActivitiesRelation> list2 = list.get(i).getUserActivitiesRelations();
				for(UserActivitiesRelation u:list2){
					if( sessionId.equals(u.getUser().getId())){
						mak[i]=1;
					}
				}
			}
			model.addAttribute("page", page);
			model.addAttribute("mak", mak);
			model.addAttribute("value", value);
			model.addAttribute("status", status);
			model.addAttribute("userId", userId);
			model.addAttribute("hot", hot);
			return "mobile/notice/listActivities";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mobile/notice/listActivities";
	}

	@RequestMapping(value = "joinActivities/{id}")
	public String joinActivities(@PathVariable String id, Model model) {
		try {
			Activities activities = new Activities();
			activities.setId(id);
			User user = new User();
			user.setId(this.getSessionUser().getId());
			UserActivitiesRelation userActivitiesRelation = new UserActivitiesRelation();
			userActivitiesRelation.setActivities(activities);
			userActivitiesRelation.setUser(user);
			userActivitiesRelation.setTime(System.currentTimeMillis());
			boolean b = this.userActivitiesRelationService
					.saveUserActivitiesRelation(userActivitiesRelation);
			if (b) {
				return "redirect:/mobile/activities/listActivities";
			} else {
				return "redirect:/mobile/activities/listActivities";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/selectJoinActivities")
	public String selectJoinActivities(String value, Integer status,
			String userId, Integer hot, Integer currentPage, Integer pageSize,
			Model model) {
		try {
			page = this.userActivitiesRelationService.listUserActivitiesRelations("羽毛", status, "0", hot,
				currentPage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/modifyActivities/{id}")
	public String modifyActivities(@PathVariable String id, Model model,
			Activities activities, MultipartFile file) {

		try {
			activities.setId(id);
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
				activities.setImg(VirtualPath);
				model.addAttribute("VirtualPath", VirtualPath);
			}
			boolean b = this.activitiesService.modifyActivities(activities);
			if (b) {
				return "redirect:/mobile/activities/listActivities";
			} else {
				return "redirect:/mobile/activities/listActivities";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mobile/activities/listActivities";

	}

}
