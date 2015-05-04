package com.meigu.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meigu.community.db.pojo.UserActivitiesRelation;
import com.meigu.community.util.persistence.model.Paginate;

@Service
@Transactional
public interface UserActivitiesRelationService{
	
	
	public Boolean saveUserActivitiesRelation(UserActivitiesRelation UserActivitiesRelation) throws Exception;
	public Boolean removeUserActivitiesRelation(String id) throws Exception;
	public UserActivitiesRelation getUserActivitiesRelation(String id) throws Exception;
	/**
	 * @param value 
	 * @param status 活动状态
	 * @param userId 报名活动的业主id
	 * @param hot    火热程度
	 * @param cpage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Paginate listUserActivitiesRelations(String value,Integer status,String userId,Integer hot,Integer cpage,Integer pageSize) throws Exception;
	
}
