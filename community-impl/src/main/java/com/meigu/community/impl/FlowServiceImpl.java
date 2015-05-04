package com.meigu.community.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.meigu.community.dao.FlowDao;
import com.meigu.community.db.pojo.AdditionalFile;
import com.meigu.community.db.pojo.FlowCommit;
import com.meigu.community.db.pojo.FlowHistory;
import com.meigu.community.db.pojo.FlowMain;
import com.meigu.community.db.pojo.FlowParameter;
import com.meigu.community.db.pojo.FlowStatus;
import com.meigu.community.db.pojo.FlowValue;
import com.meigu.community.db.pojo.User;
import com.meigu.community.service.FlowService;
import com.meigu.community.util.persistence.model.Paginate;
import com.meigu.community.vo.FlowBrief;
import com.meigu.community.vo.FlowHistoryBrief;
import com.meigu.community.vo.FlowKeyValueBrief;

@Repository
public class FlowServiceImpl implements FlowService {

	@Autowired
	private FlowDao dao;

	@Override
	public String addFlowCommit(FlowCommit flow, User user) throws Exception {
		Long now = System.currentTimeMillis();

		FlowStatus fs = new FlowStatus();
		fs.setId(1);
		flow.setFlowStatus(fs);

		flow.setCreateTime(now);

		List<FlowHistory> his = new ArrayList<FlowHistory>();
		FlowHistory fh = new FlowHistory();
		fh.setFlowCommit(flow);
		fh.setFlowStatus(fs);
		fh.setTime(now);
		fh.setUser(user);
		fh.setDescr("");
		his.add(fh);
		flow.setHistorys(his);

		for (FlowValue fv : flow.getValues()) {
			fv.setFlowCommit(flow);
		}
		return dao.saveFlowCommit(flow);
	}

	@Override
	public FlowCommit getCommit(String id) {
		return dao.getCommit(id);
	}

	@Override
	public FlowStatus getStatus(Integer status) {
		return dao.getStatus(status);
	}

	@Override
	public FlowMain getMain(Integer id) {
		return dao.getMain(id);
	}

	@Override
	public FlowBrief getFlowBrief(String id) {
		return dao.getFlowBrief(id);
	}

	/** 获取流程参数 **/
	@Override
	public List<FlowKeyValueBrief> paramlist(String commitId) {
		return dao.paramlist(commitId);
	}

	/** 获取流程操作记录历史 **/
	@Override
	public List<FlowHistoryBrief> historyList(String commitId) {
		return dao.historyList(commitId);
	}

	@Override
	public Boolean examineFlow(FlowCommit commit, FlowStatus status,
			String reason, User user) throws Throwable {
		Boolean flag = true;
		Long now = System.currentTimeMillis();
		try {
			if (commit != null && status != null && user != null) {
				FlowHistory history = new FlowHistory();
				history.setTime(now);
				history.setDescr(reason);
				history.setFlowCommit(commit);
				history.setFlowStatus(status);
				history.setUser(user);
				dao.save(history);
				commit.setFlowStatus(status);
				dao.save(commit);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Throwable e) {
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public Paginate listMyCategoryFlow(Integer mainId, Integer currentPage,
			Integer pageSize, User user) throws Exception {
		if (currentPage == null)
			currentPage = 1;
		return dao.listMyCategoryFlow(mainId, currentPage, pageSize,
				user.getId());
	}

	/**
	 * 查询流程列表
	 * 
	 * @param name
	 *            客户用户名或真实姓名
	 * @param type
	 *            流程类型(mainId)
	 * @param status
	 *            流程状态
	 * @param userId
	 *            申请用户Id
	 * @throws Exception
	 */
	public Paginate flowBriefList(String name, String type, Integer status,
			String userId, Integer cpage, Integer pageSize) throws Exception {
		return dao.flowBriefList(name, type, status, userId, cpage, pageSize);
	}

	@Override
	public List<FlowParameter> getFlowParamByFlowId(Integer mainId)
			throws Exception {
		return dao.selectFlowParamsById(mainId);
	}

	@Override
	public List<FlowMain> flowlist() {
		return dao.flowlist();
	}

	@Override
	public FlowCommit getFlowCommitById(String id) throws Exception {
		return dao.selectFlowCommitById(id);
	}

	@Override
	public void deleteFlowCommitById(String id) throws Exception {
		dao.deleteFlowCommitById(getFlowCommitById(id));
	}

	@Override
	public Boolean addFlowCommitFiles(List<AdditionalFile> additionalFiles)
			throws Exception {
		return dao.addFlowCommitFiles(additionalFiles);
	}

	@Override
	public Paginate listFlowCommitFiles(String commitId, Integer cpage,
			Integer pageSize) throws Exception {
		return dao.listFlowCommitFiles(commitId, cpage, pageSize);

	}
}
