package com.meigu.community.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.meigu.community.dao.NoticeDao;
import com.meigu.community.db.pojo.Notice;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
public class NoticeDaoImpl extends BaseDao<Notice> implements NoticeDao {

	@Override
	public Boolean saveNotice(Notice notice) throws Exception {
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
	public Boolean removeNotice(String id) throws Exception {
		Boolean flag = false;
		Notice notice = this.get(Notice.class, id);
		try {
			super.delete(notice);
			flag = true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Paginate listNotices(String value, Integer status, String userId,Long startTime,Long endTime,
			Integer cpage, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from Notice as n where 1=1  ");

		if (!StringUtil.isEmpty(value)) {
			hql.append(" and (n.title like :value or n.content like :value or n.user.realName like :value)");
			params.put("value", "%" + value + "%");
		}
		if (status != null) {
			hql.append(" and n.status = :status");
			params.put("status", status);
		}else{
			hql.append(" and n.status != :status");
			params.put("status",2 );
		}
		if (!StringUtil.isEmpty(userId)) {
			hql.append(" and n.user.id = :userId");
			params.put("userId", userId);
		}
		
		if (startTime != null) {
			hql.append(" and n.createTime >= :startTime");
			params.put("startTime", startTime);
		}
		
		if ( endTime != null) {
			hql.append(" and n.createTime <= :endTime");
			params.put("endTime", endTime);
		}
		 
		hql.append(" order by n.createTime desc");
		
		return query(hql.toString(), params, cpage, pageSize);
	}

	@Override
	public Notice getNotice(String id) throws Exception {
		return (Notice) get(Notice.class, id);
	}

	@Override
	public Boolean modifyNotice(Notice notice) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("update Notice n set n.id = :id ");
		params.put("id", notice.getId());
		if (!StringUtil.isEmpty(notice.getTitle())) {
			hql.append(" , n.title = :title ");
			params.put("title", notice.getTitle());
		}

		if (!StringUtil.isEmpty(notice.getImg())) {
			hql.append(" , n.img = :img");
			params.put("img", notice.getImg());
		}

		if (!StringUtil.isEmpty(notice.getContent())) {
			hql.append(" , n.content = :content ");
			params.put("content", notice.getContent());
		}

		if (notice.getStatus() != null) {
			hql.append("  , n.status = :status ");
			params.put("status", notice.getStatus());
		}

		hql.append(" where n.id = :id");

		Integer i = super.executeHql(hql.toString(), params);

		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

}
