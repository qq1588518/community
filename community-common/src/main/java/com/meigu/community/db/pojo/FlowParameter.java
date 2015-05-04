package com.meigu.community.db.pojo;

import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "flow_parameter")
public class FlowParameter  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name",  nullable = false)
	private String name;//参数名
	
	@Column(name = "type",  nullable = false)
	private Integer type;//'类型：1文本，2单选，3时间控件，4textarea'
	
	@Column(name = "sort_index",  nullable = false)
	private Integer sortIndex;//'排序字段',
	
	@Column(name = "enum",  nullable = false)
	private String enums;//'如果为选择，该字段为枚举值，格式如下：男：1；女：0；
	            
	@ManyToOne(targetEntity = FlowMain.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "main_id")
	private FlowMain main;

	@OneToMany(targetEntity=FlowValue.class,mappedBy="flowParameter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowValue> values;
	
	
	
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getEnums() {
		return enums;
	}

	public void setEnums(String enums) {
		this.enums = enums;
	}

	public FlowMain getMain() {
		return main;
	}

	public void setMain(FlowMain main) {
		this.main = main;
	}

	public List<FlowValue> getValues() {
		return values;
	}

	public void setValues(List<FlowValue> values) {
		this.values = values;
	}

	
	
}
