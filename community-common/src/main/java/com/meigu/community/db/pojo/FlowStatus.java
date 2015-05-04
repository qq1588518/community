package com.meigu.community.db.pojo;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "flow_status")
public class FlowStatus  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name",  nullable = false)
	private String name;//状态名
	
	@Column(name = "css",  nullable = false)
	private String css;//样式名

	@ManyToOne(targetEntity=FlowStatus.class,cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="next_status_id")
	private	FlowStatus	nextStatus;	//下一个流程状态
	
	@OneToMany(targetEntity=FlowCommit.class,mappedBy="flowStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowCommit> commits;
	
	@OneToMany(targetEntity=FlowHistory.class,mappedBy="flowStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowHistory> historys;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FlowCommit> getCommits() {
		return commits;
	}

	public void setCommits(List<FlowCommit> commits) {
		this.commits = commits;
	}

	public List<FlowHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<FlowHistory> historys) {
		this.historys = historys;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public FlowStatus getNextStatus() {
		return nextStatus;
	}
	public void setNextStatus(FlowStatus nextStatus) {
		this.nextStatus = nextStatus;
	}
	
}
