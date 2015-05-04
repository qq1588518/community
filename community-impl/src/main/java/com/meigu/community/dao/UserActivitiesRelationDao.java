package com.meigu.community.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meigu.community.db.pojo.UserActivitiesRelation;
import com.meigu.community.util.persistence.model.Paginate;

@Service
@Transactional
public interface UserActivitiesRelationDao{
	
	
	public Boolean saveUserActivitiesRelation(UserActivitiesRelation UserActivitiesRelation) throws Exception;
	public Boolean removeUserActivitiesRelation(String id) throws Exception;
	public UserActivitiesRelation getUserActivitiesRelation(String id) throws Exception;
	public Paginate listUserActivitiesRelations(String value,Integer status,String userId,Integer hot,Integer cpage,Integer pageSize) throws Exception;
	
}
