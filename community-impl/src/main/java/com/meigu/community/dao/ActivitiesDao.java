package com.meigu.community.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meigu.community.db.pojo.Activities;
import com.meigu.community.util.persistence.model.Paginate;

@Service
@Transactional
public interface ActivitiesDao {
	
	
	/**
	 * 增加一个公告am notice
	 * @return
	 * @throws Exception
	 */
	public Boolean saveActivities(Activities notice) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean removeActivities(String id) throws Exception;
	
	
	/** 查询活动
	 * @param Value 查询条件
	 * @param status 查询状态
	 * @param userId 创建人id
	 * @param cpage 第几页
	 * @param pageSize 每页多少记录
	 * @return
	 * @throws Exception
	 */
	public Paginate listActivities(String Value,Integer status,String userId,Integer hot ,Long startTime,Long endTime ,Integer cpage,Integer pageSize) throws Exception;
	
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Activities getActivities(String id) throws Exception;
	
	public Boolean modifyActivities(Activities activities) throws Exception;
	
}
