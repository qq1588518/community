package com.meigu.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meigu.community.db.pojo.Notice;
import com.meigu.community.util.persistence.model.Paginate;

@Service
@Transactional
public interface NoticeService {
	
	
	/**
	 * 增加一个公告
	 * @param notice
	 * @return
	 * @throws Exception
	 */
	public Boolean saveNotice(Notice notice) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean removeNotice(String id) throws Exception;
	
	
	/** 查询公告
	 * @param Value 查询条件
	 * @param status 查询状态
	 * @param userId 创建人id
	 * @param cpage 第几页
	 * @param pageSize 每页多少记录
	 * @return
	 * @throws Exception
	 */
	public Paginate listNotices(String Value,Integer status,String userId,Long startTime,Long endTime,Integer cpage,Integer pageSize) throws Exception;
	
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Notice getNotice(String id) throws Exception;
	
	public Boolean modifyNotice(Notice notice) throws Exception;
	
	
}
