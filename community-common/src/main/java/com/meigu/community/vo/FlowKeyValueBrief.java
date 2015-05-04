package com.meigu.community.vo;

import java.io.*;

/**
 * 流程内容参数明细
 * */
public class FlowKeyValueBrief implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private		String			id;
	private		String			commitId;		//申请Id
	private		Integer			flowType;		//申请类型
	private 	String 			flowName;		//类型名称
	private		Integer			paramId;		//参数Id
	private		String			key;			//参数名称
	private		String			value;			//参数值
	private		Integer			rank;			//参数排序
	private		Integer			type;			//参数类型
	
	public FlowKeyValueBrief() {
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
	public Integer getParamId() {
		return paramId;
	}
	public void setParamId(Integer paramId) {
		this.paramId = paramId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
