package com.meigu.community.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meigu.community.dao.NoticeDao;
import com.meigu.community.db.pojo.Notice;
import com.meigu.community.service.NoticeService;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private  NoticeDao dao;
	
	@Override
	public Boolean saveNotice(Notice notice) throws Exception {
		return dao.saveNotice(notice);
	}

	@Override
	public Boolean removeNotice(String id) throws Exception {
		return dao.removeNotice(id);
	}

	@Override
	public Paginate listNotices(String Value, Integer status, String userId,Long startTime,Long endTime,
			Integer cpage, Integer pageSize) throws Exception {
		return dao.listNotices(Value, status, userId,startTime ,endTime,cpage, pageSize);
	}

	@Override
	public Notice getNotice(String id) throws Exception {
		return dao.getNotice(id);
	}

	@Override
	public Boolean modifyNotice(Notice notice) throws Exception {
		return dao.modifyNotice(notice);
	}
	
	
}
