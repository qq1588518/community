package com.meigu.community.db.pojo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.meigu.community.util.common.DateUtil;

@Entity
@Table(name = "flow_value")
public class FlowValue  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "value",  nullable = false)
	private String value;//参数值
	
	@ManyToOne(targetEntity = FlowCommit.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "commit_id")
	private FlowCommit flowCommit;
	
	@ManyToOne(targetEntity = FlowParameter.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "para_id")
	private FlowParameter flowParameter;
	
	 
	 public String getTimeStr(){
		 return DateUtil.longDateToStr(Long.parseLong(value));
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FlowCommit getFlowCommit() {
		return flowCommit;
	}

	public void setFlowCommit(FlowCommit flowCommit) {
		this.flowCommit = flowCommit;
	}

	public FlowParameter getFlowParameter() {
		return flowParameter;
	}

	public void setFlowParameter(FlowParameter flowParameter) {
		this.flowParameter = flowParameter;
	}
	
	
	   
}
