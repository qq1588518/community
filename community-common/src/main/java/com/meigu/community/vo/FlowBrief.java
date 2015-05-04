package com.meigu.community.vo;

import java.io.Serializable;
import java.math.BigInteger;
import com.meigu.community.util.common.*;

public class FlowBrief implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private 		String 		id;
	private 		Long 		time;
	private			Integer		flowType;
	private 		String 		flowName;
	private 		Integer 	status;
	private 		String 		statusName;
	private			Integer		nextStatus;
	private			String		userId;
	private			String		userName;
	private			String		realName;
	private			String		telephone;
	private 		String 		timeStr;
	private String css;
	
	
	public FlowBrief(String id, Long time, String flowName, String statusName,String css) {
		super();
		this.id = id;
		this.time = time;
		this.flowName = flowName;
		this.statusName = statusName;
		this.css=css;
	}
	
	public FlowBrief(String id, Long time, String flowName, Integer status) {
		super();
		this.id = id;
		this.time = time;
		this.flowName = flowName;
		this.status = status;
	}
	
	public FlowBrief(String id, Long time,Integer flowType, String flowName, 
			Integer status, String userId, String userName,String telephone ,String realName) {
		super();
		this.id = id;
		this.time = time;
		this.flowType = flowType;
		this.flowName = flowName;
		this.status = status;
		this.userId = userId;
		this.userName = userName;
		this.telephone=telephone;
		this.realName = realName;
	}


	public FlowBrief() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(BigInteger time) {
		if (time!=null) {
			this.time = time.longValue();
		}
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
	
	public Integer getNextStatus() {
		return nextStatus;
	}
	public void setNextStatus(Integer nextStatus) {
		this.nextStatus = nextStatus;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTimeStr() {
		this.setTimeStr(DateUtil.format(time, "yyyy-MM-dd HH:mm"));
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}
	
}
