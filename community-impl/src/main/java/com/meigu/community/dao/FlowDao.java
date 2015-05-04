package com.meigu.community.dao;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meigu.community.db.pojo.AdditionalFile;
import com.meigu.community.db.pojo.FlowCommit;
import com.meigu.community.db.pojo.FlowHistory;
import com.meigu.community.db.pojo.FlowMain;
import com.meigu.community.db.pojo.FlowParameter;
import com.meigu.community.db.pojo.FlowStatus;
import com.meigu.community.util.persistence.model.Paginate;
import com.meigu.community.vo.FlowBrief;
import com.meigu.community.vo.FlowHistoryBrief;
import com.meigu.community.vo.FlowKeyValueBrief;

@Service
public interface FlowDao {
	
	String saveFlowCommit(FlowCommit flow) throws Exception;
	
	public		FlowCommit		getCommit(String id);
	public 		FlowStatus		getStatus(Integer status);
	public		FlowMain		getMain(Integer  id);
	public 		FlowBrief		getFlowBrief(String id);
	
	/**获取流程参数**/
	public 			List<FlowKeyValueBrief>  	paramlist(String commitId);
	/**获取流程操作记录历史**/
	public 			List<FlowHistoryBrief> 		historyList(String commitId);
	
	public 		Boolean 		save(FlowCommit 	commit) throws Throwable;
	public 		Boolean 		save(FlowHistory 	history) throws Throwable;
	
	public Paginate listMyCategoryFlow(Integer mainId, Integer currentPage,
			Integer pageSize, String userId) throws Exception;
	/**
	 * 查询流程列表
	 * @param name    	客户用户名或真实姓名
	 * @param type		流程类型(mainId)
	 * @param status	流程状态
	 * @param userId	申请用户Id
	 * @throws Exception
	 */
	public Paginate flowBriefList(String name,String type,
			Integer status,String userId,
			Integer cpage,Integer pageSize) throws Exception;
	
	public List<FlowParameter> selectFlowParamsById(Integer mainId) throws Exception;

	/**所有流程类别*/
	public List<FlowMain> flowlist();
	
	FlowCommit selectFlowCommitById(String id) throws Exception;
	
	public void deleteFlowCommitById(FlowCommit fc)throws Exception ;
	
	
	/***************************************/
	public Boolean addFlowCommitFiles(List<AdditionalFile> additionalFiles)throws Exception ;
	public Paginate listFlowCommitFiles(String commitId,Integer cpage,Integer pageSize) throws Exception;
	
}
