package com.meigu.community.dao.impl;

import java.math.*;
import java.util.*;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.meigu.community.dao.LotteryDao;
import com.meigu.community.db.pojo.Lottery;
import com.meigu.community.db.pojo.LotteryRecord;
import com.meigu.community.db.pojo.Prizes;
import com.meigu.community.db.pojo.User;
import com.meigu.community.util.common.DateUtil.Type;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;
import com.meigu.community.vo.LotteryRecordBrief;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class LotteryDaoImpl extends BaseDao implements LotteryDao {

	
	/**添加抽奖活动(包括设置的奖项)*/
	@Override
	public Boolean insertLottery(Lottery  lottery) throws Exception{
		Boolean  flag =false;
		try {
			super.save(lottery);
			flag =true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}
	
	/**更新抽奖活动(包括设置的奖项)*/
	public Boolean updateLottery(Lottery  lottery) throws Exception{
		Boolean  flag =false;
		try {
			super.getCurrentSession().merge(lottery);
			flag =true;
		} catch (Exception e) {
			flag = false;
			throw e;
		}
		return flag;
	}
	
	@Override
	public Lottery getLottery(Integer id) {
		return (Lottery) super.get(Lottery.class, id);
	}

	@Override
	public Prizes getPrizes(Integer id) {
		return (Prizes) super.get(Prizes.class, id);
	}

	
	/**
	 * 获取当前中奖的奖项
	 * @param lotteryId  抽奖的活动Id
	 * @param value		 用户抽奖产生的值
	 * @return 奖项
	 */
	@Override
	public	Prizes	getWinPrize(Integer lotteryId,Integer value){
		Prizes  prizes =null;
		try {
			StringBuffer  hql=new StringBuffer(" from Prizes  bean where 1=1 ");
			hql.append(" and bean.lottery.id =:lotteryId ");
			hql.append(" and :start >= bean.regionStart ");
			hql.append(" and :end <= bean.regionEnd ");
			hql.append(" and bean.status =0 ");
			Query query = super.getCurrentSession().createQuery(hql.toString());
			query.setParameter("lotteryId", lotteryId);
			query.setParameter("start", value);
			query.setParameter("end", value);
			prizes = (Prizes) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prizes;
	}
	
	@Override
	public	Prizes	getNonPrize(Integer lotteryId){
		Prizes  prizes =null;
		try {
			StringBuffer  hql=new StringBuffer(" from Prizes  bean where 1=1 ");
			hql.append(" and bean.lottery.id =:lotteryId ");
			hql.append(" and bean.prize  =1 ");
			hql.append(" and bean.status =0 ");
			Query query = super.getCurrentSession().createQuery(hql.toString());
			query.setParameter("lotteryId", lotteryId);
			query.setMaxResults(1).setFirstResult(0);
			List<Prizes> list=query.list();
			if (list!=null&&list.size()>0) {
				prizes=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prizes;
	}
	
	
	/**抽奖活动列表*/
	public Paginate lotterylist(String keyword,Integer rate,
			Long startDate,Long endDate,Integer status,
			Integer cpage,Integer pageSize) throws Throwable{
		Paginate  page = new Paginate();
		Map<String, Object>  params = new HashMap<String, Object>();
		try {
			StringBuffer  hql=new StringBuffer(" from Lottery bean where 1=1 ");
			if (StringUtil.isNotEmpty(keyword)) {
				hql.append(" and ( bean.name like :keyword  or bean.descr like :keyword ) ");
				params.put("keyword","%"+keyword+"%");
			}
			if (rate!=null) {
				hql.append(" and bean.rate = :rate ");
				params.put("rate", rate);
			}
			if (startDate!=null) {
				hql.append(" and bean.startDate >=:startDate");
				params.put("startDate", startDate);
			}
			if (endDate!=null) {
				hql.append(" and bean.endDate <= :endDate");
				params.put("endDate", endDate);
			}
			if (status!=null) {
				hql.append(" and bean.status = :status ");
				params.put("status", status);
			}
			page = super.query(hql.toString(), params, cpage, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return page;
	}
	
	@Override
	public Paginate awardlist(String keyword,Long startDate,Long endDate,Integer cpage,Integer pageSize){
		Paginate  page = new Paginate();
		Map<String, Object>  params = new HashMap<String, Object>();
		try {
			StringBuffer  sql = new StringBuffer("select 	");
			sql.append(" lr.id,");
			sql.append(" lr.l_id	as lotteryId, ");
			sql.append(" l.name	as lotteryName, ");
			sql.append(" l.rate	as rate, ");
			sql.append(" l.times as times, ");
			sql.append(" l.multiple, ");
			sql.append(" l.radix, ");
			sql.append(" l.start_date as startDate, ");
			sql.append(" l.end_date   as endDate, ");
			sql.append(" l.status as lotteryStatus, ");
			sql.append(" lr.p_id	as prizeId, ");
			sql.append(" p.name  as awardName, ");
			sql.append(" p.region_start as regionStart, ");
			sql.append(" p.region_end as regionEnd, ");
			sql.append(" p.rank, ");
			sql.append(" p.chance, ");
			sql.append(" p.prize, ");
			sql.append(" p.status as prizeStatus, ");
			sql.append(" lr.create_time as createDate, ");
			sql.append(" lr.user_id as userId, ");
			sql.append(" u.username, ");
			sql.append(" u.real_name as realName, ");
			sql.append(" u.mobile as mobilePhone ");
			sql.append(" from lottery_record lr  ");
			sql.append(" left join lottery l on (l.id=lr.l_id) ");
			sql.append(" left join prizes  p on (p.id=lr.p_id) ");
			sql.append(" left join user    u on (u.id=lr.user_id) ");
			sql.append(" where 1=1 and p.prize = 0 ");
			if (StringUtil.isNotEmpty(keyword)) {
				sql.append(" and (l.name like :keyword or p.name like :keyword )");
				params.put("keyword", "%"+keyword.trim()+"%");
			}
			if (startDate!=null) {
				sql.append(" and lr.create_time >=:startDate ");
				params.put("startDate", startDate);
			}
			if (endDate!=null) {
				sql.append(" and lr.create_time <=:endDate ");
				params.put("endDate", endDate);
			}
			sql.append(" order by lr.create_time desc ");
			page  = super.sqlQuery(sql.toString(), params, cpage, pageSize, LotteryRecordBrief.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**添加抽奖记录*/
	@Override
	public Boolean insertAward(Lottery lottery,Prizes prizes,User user) throws Exception {
		Boolean  flag=false;
		try {
			LotteryRecord  record =new LotteryRecord();
			record.setCreateDate(System.currentTimeMillis());
			record.setLottery(lottery);
			record.setPrizes(prizes);
			record.setUser(user);
			super.save(record);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return  flag;
	}
	
	@Override
	public List<Prizes> prizeslisList(Integer lotteryId) throws Exception{
		Map<String, Object>  param =new HashMap<String, Object>();
		StringBuffer  hql=new StringBuffer("from Prizes bean where 1=1 ");
		hql.append(" and bean.lottery.id = :lotteryId ");
		hql.append(" and bean.status = 0 ");
		return find(hql.toString(), param);
	}

	@Override
	public Integer	playCount(String userId,Integer lotteryId,Long date,Type type){
		Integer count = 0;
		try{
			StringBuffer  hql=new StringBuffer("select count(*) from lottery_record lr ");
			hql.append(" left join lottery l on (l.id=lr.l_id) ");
			hql.append(" left join prizes  p on (p.id=lr.p_id) ");
			hql.append(" left join user  u  on (u.id=lr.user_id) ");
			hql.append(" where 1=1 ");
			hql.append(" and lr.l_id= :lotteryId ");
			hql.append(" and lr.user_id= :userId ");
			hql.append(" and from_unixtime(lr.create_time/1000,'%Y-%m-%d')=from_unixtime(:date/1000,'%Y-%m-%d') ");
			Query  query = super.getCurrentSession().createSQLQuery(hql.toString());
			query.setParameter("lotteryId", lotteryId);
			query.setParameter("userId", userId);
			query.setParameter("date", date);
			count =((BigInteger) query.uniqueResult()).intValue();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer	winCount(String userId,Integer lotteryId,Long date,Type type){
		Integer count = 0;
		try{
			StringBuffer  hql=new StringBuffer("select count(*) from lottery_record lr ");
			hql.append(" left join lottery l on (l.id=lr.l_id) ");
			hql.append(" left join prizes  p on (p.id=lr.p_id) ");
			hql.append(" left join user  u  on (u.id=lr.user_id) ");
			hql.append(" where 1=1 ");
			hql.append(" and lr.l_id= :lotteryId ");
			hql.append(" and lr.user_id= :userId ");
			hql.append(" and p.prize = 0 ");
			hql.append(" and p.status =0 ");
			if (type!=null) {
				if (type.equals(Type.DAY)) {
					hql.append(" and from_unixtime(lr.create_time/1000,'%Y-%m-%d')=from_unixtime(:date/1000,'%Y-%m-%d') ");
				}else if (type.equals(Type.WEEK)) {
					hql.append(" and from_unixtime(lr.create_time/1000,'%Y-%U')=from_unixtime(:date/1000,'%Y-%U') ");
				}else if (type.equals(Type.MONTH)) {
					hql.append(" and from_unixtime(lr.create_time/1000,'%Y-%m')=from_unixtime(:date/1000,'%Y-%m') ");
				}else if (type.equals(Type.YEAR)) {
					hql.append(" and from_unixtime(lr.create_time/1000,'%Y')=from_unixtime(:date/1000,'%Y') ");
				}
			}else{
				hql.append(" and (:date > 0) ");
			}
			Query  query = super.getCurrentSession().createSQLQuery(hql.toString());
			query.setParameter("lotteryId", lotteryId);
			query.setParameter("userId", userId);
			query.setParameter("date", date);
			count =((BigInteger) query.uniqueResult()).intValue();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
