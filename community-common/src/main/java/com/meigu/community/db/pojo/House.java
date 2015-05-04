package com.meigu.community.db.pojo;

import java.io.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "house")
public class House implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false)
	private				String			id;			//ID
	
	@Column(name = "build_no",  nullable = false)
	private				String			buildNo;	//楼号
	
	@Column(name = "unit_no",  nullable = false)
	private				String			unitNo;		//单元
	
	@Column(name = "house_name",  nullable = false)
	private				String			houseName;	//房号
	
	@Column(name = "status",  nullable = false)
	private				Integer			status	=	1;		//状态(0未售，1已售)
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private				User			user;		//业主
	
	public House() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildNo() {
		return buildNo;
	}

	public void setBuildNo(String buildNo) {
		this.buildNo = buildNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
