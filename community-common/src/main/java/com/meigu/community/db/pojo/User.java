package com.meigu.community.db.pojo;

import java.io.*;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false,updatable=false)
	private			String			id;			//id
	
	@Column(name = "username", unique = true,  nullable = false,updatable=false)
	private			String			username;	//用户名
	
	@Column(name = "password", nullable = false)
	private			String			password;	//密码
	
	@Column(name = "real_name", nullable = false)
	private			String			realName;	//真实姓名
	
	@Column(name = "mobile", nullable = false)
	private			String			mobile;		//手机号码
	
	@Column(name = "gender", nullable = false)
	private			Integer			gender;		//性别(0女，1男)
	
	@Column(name = "email")
	private			String			email;		//电子邮箱
	
	@Column(name = "id_card")
	private			String			idCard;		//身份证号码
	
	@Column(name = "type", nullable = false,updatable=false)
	private			Integer			type;		//用户类型(1超级管理员，2客户，3客服)
	
	@Column(name = "status", nullable = false)
	private			Integer			status;		//账户状态：0正常，1异常
	
	
	@OneToMany(targetEntity=FlowHistory.class,mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FlowHistory> historys;

	@OneToMany(targetEntity=Notice.class,mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Notice> notices;
	
	@OneToMany(targetEntity=UserActivitiesRelation.class,mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserActivitiesRelation> userActivitiesRelations;
	
	@OneToMany(targetEntity=Activities.class,mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Activities> activities;
	
	public User() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<FlowHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<FlowHistory> historys) {
		this.historys = historys;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public List<UserActivitiesRelation> getUserActivitiesRelations() {
		return userActivitiesRelations;
	}

	public void setUserActivitiesRelations(
			List<UserActivitiesRelation> userActivitiesRelations) {
		this.userActivitiesRelations = userActivitiesRelations;
	}

	public List<Activities> getActivities() {
		return activities;
	}

	public void setActivities(List<Activities> activities) {
		this.activities = activities;
	}
	
	
}
