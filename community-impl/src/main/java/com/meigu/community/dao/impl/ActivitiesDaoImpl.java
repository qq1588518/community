package com.meigu.community.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.meigu.community.dao.ActivitiesDao;
import com.meigu.community.db.pojo.Activities;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
public class ActivitiesDaoImpl extends BaseDao<Activities> implements
		ActivitiesDao {

	@Override
	public Boolean saveActivities(Activities notice) throws Exception {
		Boolean flag = false;
		try {
			super.save(notice);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Boolean removeActivities(String id) throws Exception {
		Boolean flag = false;
		Activities activities = this.get(Activities.class, id);
		try {
			super.delete(activities);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Paginate listActivities(String value, Integer status, String userId,
			Integer hot,Long startTime,Long endTime ,Integer cpage, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from Activities as a where 1=1  ");

		if (!StringUtil.isEmpty(value)) {
			hql.append(" and (a.title like :value or a.content like :value or a.user.realName like :value)");
			params.put("value", "%" + value + "%");
		}
		if (status != null) {
			hql.append(" and a.status = :status");
			params.put("status", status);
		}else{
			hql.append(" and a.status != 2 ");
		}
		if (!StringUtil.isEmpty(userId)) {
			hql.append(" and a.user.id = :userId");
			params.put("userId", userId);
		}
		if (hot != null) {
			hql.append(" and a.hot = :hot");
			params.put("hot", hot);
		}
		if (startTime != null) {
			hql.append(" and a.time >= :startTime");
			params.put("startTime", startTime);
		}
		if (endTime != null) {
			hql.append(" and a.time <= :endTime");
			params.put("endTime", endTime);
		}

		hql.append(" order by a.hot desc ,a.time desc");

		return query(hql.toString(), params, cpage, pageSize);
	}

	@Override
	public Activities getActivities(String id) throws Exception {
		return (Activities) get(Activities.class, id);
	}

	@Override
	public Boolean modifyActivities(Activities activities) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("update Activities a set a.id=:id ");
		if (!StringUtil.isEmpty(activities.getTitle())) {
			hql.append(" , a.title=:title ");
			params.put("title", activities.getTitle());
		}
		if (!StringUtil.isEmpty(activities.getImg())) {
			hql.append(" , a.img=:img");
			params.put("img", activities.getImg());
		}
		if (!StringUtil.isEmpty(activities.getContent())) {
			hql.append(" , a.content=:content ");
			params.put("content", activities.getContent());
		}
		if (activities.getTime() != null) {
			hql.append("  , a.time=:time ");
			params.put("time", activities.getTime());
		}
		if (activities.getPeopleLow() != null) {
			hql.append("  , a.peopleLow=:peopleLow ");
			params.put("peopleLow", activities.getPeopleLow());
		}
		if (activities.getPeopleHigh() != null) {
			hql.append("  , a.peopleHigh=:peopleHigh ");
			params.put("peopleHigh", activities.getPeopleHigh());
		}
		if (activities.getAlreadyNum() != null) {
			hql.append("  , a.alreadyNum=:alreadyNum ");
			params.put("alreadyNum", activities.getAlreadyNum());
		}
		if (activities.getStatus() != null) {
			hql.append("  , a.status=:status ");
			params.put("status", activities.getStatus());
		}
		
		if (activities.getHot() != null) {
			hql.append("  , a.hot=:hot ");
			params.put("hot", activities.getHot());
		}
		
		hql.append(" where a.id=:id ");
		params.put("id", activities.getId());
		
		Integer i = super.executeHql(hql.toString(), params);

		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}
}
