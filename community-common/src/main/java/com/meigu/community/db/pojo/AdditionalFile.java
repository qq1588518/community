package com.meigu.community.db.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "additional_file")
public class AdditionalFile  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@ManyToOne(targetEntity = FlowCommit.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "commit_id")
	private FlowCommit flowCommit;
	
	@Column(name = "path",  nullable = false)
	private String path;//流程名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FlowCommit getCommit() {
		return flowCommit;
	}

	public void setCommit(FlowCommit flowCommit) {
		this.flowCommit = flowCommit;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
