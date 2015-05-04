package com.meigu.community.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meigu.community.db.pojo.Activities;
import com.meigu.community.service.ActivitiesService;
import com.meigu.community.service.TimerService;

@Component
@SuppressWarnings("unchecked")
public class TimerServiceImpl implements TimerService{

	@Autowired
	private ActivitiesService activitiesService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	@Override
	public void modifyActivitiesStatusByTime() {
		
		
		try {
			List<Activities> list = (List<Activities>) activitiesService.listActivities(null, null, null,null,null, null, null, null).getData();
			for(Activities a : list){
				if(a.getTime()<System.currentTimeMillis()){
					a.setStatus(1);
					activitiesService.modifyActivities(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
