package com.meigu.community.vo;

import java.io.Serializable;
import java.math.BigInteger;

/**操作历史明细类*/
public class FlowHistoryBrief  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private			String			id;
	private			String			commitId;		//申请Id
	private			Integer			flowType;		//申请类型
	private 		String 			flowName;		//类型名称
	private			Integer			status;			//状态值
	private			String			statusName;		//状态名称
	private			String			userId;			//用户Id
	private			String			userName;		//用户名
	private			String			realName;		//用户姓名
	private			String			telephone;		//用户电话
	private			Long			time;			//操作时间
	private			String			desc;			//备注
	
	public FlowHistoryBrief() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(BigInteger time) {
		if (time!=null) {
			this.time = time.longValue();
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
