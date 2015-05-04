package com.meigu.community.db.pojo;

import java.util.*;
import java.io.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "flow_main")
public class FlowMain implements Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name",  nullable = false)
	private String name;//流程名
	
	@OneToMany(targetEntity=FlowParameter.class,mappedBy="main", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowParameter> params;

	@OneToMany(targetEntity=FlowCommit.class,mappedBy="main", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowCommit> commits;
	
	
	
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

	public List<FlowParameter> getParams() {
		return params;
	}

	public void setParams(List<FlowParameter> params) {
		this.params = params;
	}

	public List<FlowCommit> getCommits() {
		return commits;
	}

	public void setCommits(List<FlowCommit> commits) {
		this.commits = commits;
	}
	
	
	
	
}
