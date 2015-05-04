package com.meigu.community.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meigu.community.dao.ActivitiesDao;
import com.meigu.community.dao.UserActivitiesRelationDao;
import com.meigu.community.db.pojo.Activities;
import com.meigu.community.db.pojo.UserActivitiesRelation;
import com.meigu.community.service.UserActivitiesRelationService;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
@Transactional
public class UserActivitiesRelationServiceImpl implements
		UserActivitiesRelationService {

	@Autowired
	private UserActivitiesRelationDao dao;
	@Autowired
	private ActivitiesDao activitiesDao;

	@Override
	public Boolean saveUserActivitiesRelation(
			UserActivitiesRelation UserActivitiesRelation) throws Exception {
		Boolean flag = false;
		try {
			Boolean b = dao.saveUserActivitiesRelation(UserActivitiesRelation);
			Activities activities = activitiesDao
					.getActivities(UserActivitiesRelation.getActivities()
							.getId());
			activities.setAlreadyNum(activities.getAlreadyNum() + 1);
			Boolean newb = activitiesDao.modifyActivities(activities);
			if (b && newb) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Boolean removeUserActivitiesRelation(String id) throws Exception {
		return dao.removeUserActivitiesRelation(id);
	}

	@Override
	public UserActivitiesRelation getUserActivitiesRelation(String id)
			throws Exception {
		return dao.getUserActivitiesRelation(id);
	}

	@Override
	public Paginate listUserActivitiesRelations(String value, Integer status,
			String userId, Integer hot, Integer cpage, Integer pageSize)
			throws Exception {
		return dao.listUserActivitiesRelations(value, status, userId, hot,
				cpage, pageSize);
	}

}
