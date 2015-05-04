package com.meigu.community.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.meigu.community.vo.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.persistence.model.*;

@Service
@Transactional
public interface FlowService {

	/**
	 * 发起一个流程
	 * @param flow
	 * @return
	 * @throws Exception
	 */
	String addFlowCommit(FlowCommit flow,User user) throws Exception;
	
	FlowCommit		getCommit(String id);
	FlowStatus		getStatus(Integer status);
	FlowMain		getMain(Integer id);
	FlowBrief		getFlowBrief(String id);
	/**获取流程参数**/
	public 			List<FlowKeyValueBrief>  	paramlist(String commitId);
	/**获取流程操作记录历史**/
	public 			List<FlowHistoryBrief> 		historyList(String commitId);
	
	/**审核流程*/
	public Boolean examineFlow(FlowCommit commit,FlowStatus	status,String reason,User user) throws Throwable;
	
	/**
	 * 分页获取该用户不同流程的列表
	 * @param mainId
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Paginate listMyCategoryFlow(Integer mainId,Integer currentPage,Integer pageSize,User user) throws Exception;
	
	/**
	 * 查询流程列表
	 * @param name    	客户用户名或真实姓名
	 * @param type		流程类型(mainId)
	 * @param status	流程状态
	 * @param userId	申请用户Id
	 * @throws Exception
	 */
	public Paginate flowBriefList(String name,String type,
			Integer status,String userId,Integer cpage,Integer pageSize) throws Exception;
	
	/**
	 * 获取指定流程的所有属性
	 * @param mainId
	 * @return
	 * @throws Exception
	 */
	List<FlowParameter> getFlowParamByFlowId(Integer mainId) throws Exception;
	
	
	
	/**所有流程类别*/
	public List<FlowMain> flowlist();
	/**
	 * 根据已提交流程id获取该流程对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FlowCommit getFlowCommitById(String id) throws Exception;
	/**
	 *删除指定id的已提交流程
	 * @param id
	 * @throws Exception
	 */
	public void deleteFlowCommitById(String id)throws Exception ;
	
	
	/**上床附件
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Boolean addFlowCommitFiles(List<AdditionalFile> list) throws Exception ;
	/**查询附件
	 * @param commitId
	 * @param cpage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Paginate listFlowCommitFiles(String commitId,Integer cpage,Integer pageSize) throws Exception;
	
}
