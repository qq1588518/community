package com.meigu.community.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.meigu.community.dao.UserActivitiesRelationDao;
import com.meigu.community.db.pojo.UserActivitiesRelation;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;

@Repository
public class UserActivitiesRelationDaoImpl extends
		BaseDao<UserActivitiesRelation> implements UserActivitiesRelationDao {

	@Override
	public Boolean saveUserActivitiesRelation(
			UserActivitiesRelation UserActivitiesRelation) throws Exception {
		boolean b = false;

		try {
			super.save(UserActivitiesRelation);
			b = true;
		} catch (Exception e) {
			b = false;
			throw e;
		}
		return b;
	}

	@Override
	public Boolean removeUserActivitiesRelation(String id) throws Exception {
		boolean b = false;
		try {
			super.delete(this.getUserActivitiesRelation(id));
			b = true;
		} catch (Exception e) {
			b = false;
			throw e;
		}
		return b;
	}

	@Override
	public UserActivitiesRelation getUserActivitiesRelation(String id)
			throws Exception {
		return super.get(UserActivitiesRelation.class, id);
	}

	@Override
	public Paginate listUserActivitiesRelations(String value, Integer status,
			String userId, Integer hot, Integer cpage, Integer pageSize)
			throws Exception {
		StringBuffer hql = new StringBuffer(
				" from UserActivitiesRelation uar where 1=1 ");
//		StringBuffer hql = new StringBuffer(
//				" from UserActivitiesRelation uar left join uar.activities as a  left join uar.user as u where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (!StringUtil.isEmpty(value)) {
			hql.append(" and ( uar.activities.title like :value or  uar.activities.content like :value)");
			params.put("value", "%" + value + "%");
		}
		if (status != null) {
			hql.append(" and  uar.activities.status = :status" );
			params.put("status", status);
		}
		if (!StringUtil.isEmpty(userId)) {
			hql.append(" and  uar.user.id = :userId ");
			params.put("userId", userId);
		}
		if (hot != null) {
			hql.append(" and  uar.activities.hot = :hot ");
			params.put("hot", hot);
		}
		 hql.append(" order  by uar.activities.time desc" );
		 
		return query(hql.toString(), params, cpage, pageSize);
	}

}
