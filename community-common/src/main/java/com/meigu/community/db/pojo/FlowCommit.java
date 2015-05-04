package com.meigu.community.db.pojo;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "flow_commit")
public class FlowCommit  implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "create_time",  nullable = false)
	private Long createTime;//创建时间
	
	@ManyToOne(targetEntity = FlowMain.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "main_id")
	private FlowMain main;
	
	@ManyToOne(targetEntity = FlowStatus.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private FlowStatus flowStatus;

	@OneToMany(targetEntity=FlowValue.class,mappedBy="flowCommit", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<FlowValue> values;
	
	@OneToMany(targetEntity=FlowHistory.class,mappedBy="flowCommit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("time")
	private List<FlowHistory> historys;
	
	@OneToMany(targetEntity=AdditionalFile.class,mappedBy="flowCommit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AdditionalFile> files;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public FlowMain getMain() {
		return main;
	}

	public void setMain(FlowMain main) {
		this.main = main;
	}

	public FlowStatus getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(FlowStatus flowStatus) {
		this.flowStatus = flowStatus;
	}

	public List<FlowValue> getValues() {
		return values;
	}

	public void setValues(List<FlowValue> values) {
		this.values = values;
	}

	public List<FlowHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<FlowHistory> historys) {
		this.historys = historys;
	}

	public List<AdditionalFile> getFiles() {
		return files;
	}

	public void setFiles(List<AdditionalFile> files) {
		this.files = files;
	}

}
