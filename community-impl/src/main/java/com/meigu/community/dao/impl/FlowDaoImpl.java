package com.meigu.community.dao.impl;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.meigu.community.dao.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.KeyGenerator;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.dao.BaseDao;
import com.meigu.community.util.persistence.model.Paginate;
import com.meigu.community.vo.FlowBrief;
import com.meigu.community.vo.FlowHistoryBrief;
import com.meigu.community.vo.FlowKeyValueBrief;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class FlowDaoImpl extends BaseDao implements FlowDao {

	@Override
	public String saveFlowCommit(FlowCommit flow) throws Exception {
		return (String) super.save(flow);
	}

	
	@Override
	public FlowCommit getCommit(String id) {
		return (FlowCommit) super.get(FlowCommit.class, id);
	}
	@Override
	public FlowStatus		getStatus(Integer status){
		return (FlowStatus) super.get(FlowStatus.class, status);
	}
	@Override
	public FlowMain getMain(Integer id) {
		return (FlowMain) super.get(FlowMain.class, id);
	}

	@Override
	public FlowBrief		getFlowBrief(String id){
		FlowBrief  brief = null;
		try {
			StringBuffer sql=new StringBuffer("select ");
			sql.append("fc.id 		as id, ");
			sql.append("fc.main_id	as flowType, ");
			sql.append("fm.name		as flowName, ");
			sql.append("fc.status_id	as 'status', ");
			sql.append("fs.next_status_id as 'nextStatus', ");
			sql.append("fh.user_id	as userId, ");
			sql.append("u.username	as userName, ");
			sql.append("u.mobile	as telephone, ");
			sql.append("u.real_name	as realName, ");
			sql.append("fc.create_time	as 'time' ");
			sql.append("from flow_commit fc  ");
			sql.append("left join flow_status fs on (fs.id=fc.status_id) ");
			sql.append("left join flow_main 	fm on (fm.id=fc.main_id) ");
			sql.append("left join (flow_history	fh left join user u on (u.id=fh.user_id)) on (fh.commit_id = fc.id and fh.status_id =1 ) ");
			sql.append(" where fc.id = :id ");
			sql.append(" group by fc.id ");
			sql.append(" order by fc.create_time desc,fc.main_id ");
			
			Query query = this.getCurrentSession().createSQLQuery(sql.toString());
			query.setParameter("id", id);
			query.setResultTransformer(Transformers.aliasToBean(FlowBrief.class));
			brief =(FlowBrief) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  brief;
	}
	
	/**获取流程参数**/
	@Override
	public 			List<FlowKeyValueBrief>  	paramlist(String commitId){
		List<FlowKeyValueBrief>  list=new ArrayList<FlowKeyValueBrief>();
		try {
			StringBuffer sql=new StringBuffer("select ");
			sql.append(" fv.id, ");
			sql.append(" fv.commit_id	as commitId, ");
			sql.append(" fc.main_id	as flowType, ");
			sql.append(" fm.name		as flowName, ");
			sql.append(" fv.para_id	as paramId, ");
			sql.append(" fp.name		as 'key', ");
			sql.append(" fv.value, ");
			sql.append(" fp.sort_index	as rank, ");
			sql.append(" fp.type ");
			sql.append(" from flow_value fv ");
			sql.append(" left join (flow_commit fc left join flow_main fm on (fm.id=fc.main_id)) on (fv.commit_id=fc.id) ");
			sql.append(" left join flow_parameter fp on (fp.id=fv.para_id) ");
			sql.append(" where 1=1 ");
			sql.append(" and fc.id=:id ");
			sql.append(" order by fp.sort_index asc ");
			
			Query query = this.getCurrentSession().createSQLQuery(sql.toString());
			query.setParameter("id", commitId);
			query.setResultTransformer(Transformers.aliasToBean(FlowKeyValueBrief.class));
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**获取流程操作记录历史**/
	@Override
	public 			List<FlowHistoryBrief> 		historyList(String commitId){
		List<FlowHistoryBrief>  list=new ArrayList<FlowHistoryBrief>();
		try {
			StringBuffer sql=new StringBuffer("select ");
			sql.append(" fh.id, ");
			sql.append(" fh.commit_id	as commitId, ");
			sql.append(" fm.id		as flowType, ");
			sql.append(" fm.name		as flowName, ");
			sql.append(" fh.status_id	as 'status', ");
			sql.append(" fs.name		as statusName, ");
			sql.append(" fh.user_id	as userId, ");
			sql.append(" u.username	as userName, ");
			sql.append(" u.real_name	as realName, ");
			sql.append(" u.mobile	as telephone, ");
			sql.append(" fh.time		as 'time', ");
			sql.append(" fh.descr	as 'desc' ");
			sql.append(" from flow_history fh ");
			sql.append(" left join (flow_commit fc left join flow_main fm on (fm.id=fc.main_id)) on (fc.id=fh.commit_id) ");
			sql.append(" left join flow_status fs on (fs.id=fh.status_id) ");
			sql.append(" left join user u on (u.id=fh.user_id) ");
			sql.append(" where 1=1 and fh.commit_id= :id ");
			sql.append(" order by fh.time asc ");
			
			Query query = this.getCurrentSession().createSQLQuery(sql.toString());
			query.setParameter("id", commitId);
			query.setResultTransformer(Transformers.aliasToBean(FlowHistoryBrief.class));
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public 		Boolean 		save(FlowCommit 	commit) throws Throwable{
		Boolean  flag=false;
		try {
			saveFlowCommit(commit);
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	public 		Boolean 		save(FlowHistory 	history) throws Throwable{
		Boolean  flag=false;
		try {
			super.save(history);
			flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}
	
	@Override
	public Paginate listMyCategoryFlow(Integer mainId, Integer currentPage,
			Integer pageSize, String userId) throws Exception {
		
		String hql="select new com.meigu.community.vo.FlowBrief(fc.id,fc.createTime,fc.main.name,fc.flowStatus.name,fc.flowStatus.css) " +
				"from FlowCommit fc where fc.main.id=:mainId and fc.id in" +
				" (select fh.flowCommit.id from FlowHistory fh where fh.flowStatus.id=1 and fh.user.id=:userId) order by fc.createTime desc";
		Map q=new HashMap();
		q.put("mainId", mainId);
		q.put("userId", userId);
		return query(hql, q, currentPage, pageSize);
	}

	@Override
	public List<FlowParameter> selectFlowParamsById(Integer mainId) throws Exception {
		String hql="from FlowParameter fm where fm.main.id=:mainId order by sortIndex";
		Map q=new HashMap();
		q.put("mainId", mainId);
		return find(hql, q);
	}

	public List<FlowMain> flowlist(){
		String hql="from FlowMain";
		return this.find(hql);
	}
	
	/**
	 * 查询流程列表
	 * @param name    	客户用户名或真实姓名
	 * @param type		流程类型(mainId)
	 * @param status	流程状态
	 * @param userId	申请用户Id
	 * @throws Exception
	 */
	public Paginate flowBriefList(String name,String type,Integer status,String userId,Integer cpage,Integer pageSize) throws Exception{
		Map<String, Object> params=new HashMap<String, Object>();
		StringBuffer sql=new StringBuffer("select ");
		sql.append("fc.id 		as id, ");
		sql.append("fc.main_id	as flowType, ");
		sql.append("fm.name		as flowName, ");
		sql.append("fc.status_id	as 'status', ");
		sql.append("fs.next_status_id as 'nextStatus', ");
		sql.append("fh.user_id	as userId, ");
		sql.append("u.username	as userName, ");
		sql.append("u.mobile	as telephone, ");
		sql.append("u.real_name	as realName, ");
		sql.append("fc.create_time	as 'time' ");
		sql.append("from flow_commit fc  ");
		sql.append("left join flow_status fs on (fs.id=fc.status_id) ");
		sql.append("left join flow_main 	fm on (fm.id=fc.main_id) ");
		sql.append("left join (flow_history	fh left join user u on (u.id=fh.user_id)) on (fh.commit_id = fc.id and fh.status_id =1 ) ");
		
		sql.append("where 1=1 ");
				
		if (StringUtil.isNotEmpty(name)) {
			params.put("username", "%"+name.trim()+"%");
			params.put("realname", "%"+name.trim()+"%");
			sql.append(" and ( u.username like :username or u.real_name like :realname )");
		}
		if (StringUtil.isNotEmpty(type)) {
			params.put("type", type);
			sql.append(" and fc.main_id = :type ");
		}
		if (status!=null) {
			params.put("status", status);
			sql.append(" and fc.status_id = :status ");
		}
		if (StringUtil.isNotEmpty(userId)) {
			params.put("userId", userId);
			sql.append(" and fh.user_id = :userId ");
		}
		sql.append(" group by fc.id ");
		sql.append(" order by fc.create_time desc,fc.main_id ");
		return sqlQuery(sql.toString(), params, cpage, pageSize, FlowBrief.class);
	}
	@Override
	public FlowCommit selectFlowCommitById(String id) throws Exception {
		return (FlowCommit) get(FlowCommit.class, id);
	}
	
	public void deleteFlowCommitById(FlowCommit fc)throws Exception {
		delete(fc);
	}


	@Override
	public Boolean addFlowCommitFiles(List<AdditionalFile> additionalFiles)
			throws Exception {
		Boolean  flag=false;
		try {
		
		StringBuffer sql = new StringBuffer("insert into additional_file values ");
		List<String> params = new  ArrayList<String>();
		
		for(AdditionalFile file : additionalFiles){
			sql.append("( ?,?,?),");
			String id = KeyGenerator.UUIDGenerator();
			params.add(id);
			params.add(file.getCommit().getId());
			params.add(file.getPath());
		}
		String  newsql = sql.substring(0, sql.lastIndexOf(","));
		
		Query query = this.getCurrentSession().createSQLQuery(newsql);
		for(int i = 0;i<params.size();i++){
			query.setString(i,params.get(i) );
		}
		query.executeUpdate();
		flag=true;
		} catch (Exception e) {
			flag=false;
			throw e;
		}
		return flag;
	}


	@Override
	public Paginate listFlowCommitFiles(String commitId, Integer cpage,
			Integer pageSize) throws Exception {
		Map<String,String> params = new HashMap<String, String>();
		String hql = "from AdditionalFile as a where a.flowCommit.id = :commitId";
		params.put("commitId", commitId);
		return query(hql, params, cpage, pageSize);
	}
}
