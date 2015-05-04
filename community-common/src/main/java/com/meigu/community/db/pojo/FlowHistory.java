package com.meigu.community.db.pojo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.meigu.community.util.common.DateUtil;

@Entity
@Table(name = "flow_history")
public class FlowHistory   implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "time",  nullable = false)
	private Long time;//时间
	
	@Column(name = "descr",  nullable = false)
	private String descr;//备注
	
	@ManyToOne(targetEntity = FlowCommit.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "commit_id")
	private FlowCommit flowCommit;

	@ManyToOne(targetEntity = FlowStatus.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private FlowStatus flowStatus;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	
	
	public String getTimeStr(){
		return DateUtil.longDateToStr(time);
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

	public void setTime(Long time) {
		this.time = time;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public FlowCommit getFlowCommit() {
		return flowCommit;
	}

	public void setFlowCommit(FlowCommit flowCommit) {
		this.flowCommit = flowCommit;
	}

	public FlowStatus getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(FlowStatus flowStatus) {
		this.flowStatus = flowStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
}
