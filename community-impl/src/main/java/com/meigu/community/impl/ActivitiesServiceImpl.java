package com.meigu.community.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meigu.community.dao.ActivitiesDao;
import com.meigu.community.db.pojo.Activities;
import com.meigu.community.service.ActivitiesService;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
public class ActivitiesServiceImpl implements ActivitiesService{

	@Autowired
	private  ActivitiesDao dao;
	
	@Override
	public Boolean saveActivities(Activities Activities) throws Exception {
		return dao.saveActivities(Activities);
	}

	@Override
	public Boolean removeActivities(String id) throws Exception {
		return dao.removeActivities(id);
	}

	@Override
	public Paginate listActivities(String Value, Integer status, String userId,Integer hot ,Long startTime,Long endTime ,
			Integer cpage, Integer pageSize) throws Exception {
		return dao.listActivities(Value, status, userId,hot,startTime,endTime, cpage, pageSize);
	}

	@Override
	public Activities getActivities(String id) throws Exception {
		return dao.getActivities(id);
	}

	@Override
	public Boolean modifyActivities(Activities activities) throws Exception {
		return dao.modifyActivities(activities);
	}
	
	
}
