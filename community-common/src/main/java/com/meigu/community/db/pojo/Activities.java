package com.meigu.community.db.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "activities")
public class Activities implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@GeneratedValue(generator = "hibernate-uuid")
	@Column(name = "id", unique = true, nullable = false,updatable=false)
	private String id;
	
	@Column(name = "title",  nullable = false)
	private String title;//标题
	
	@Column(name = "img",  nullable = true)
	private String img;//图片
	
	@Column(name = "content",  nullable = false)
	private String content;//内容
	
	@Column(name = "time",  nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Long time;//活动日期（哪天）
	
	@Column(name = "create_time",  nullable = false)
	private Long createTime;//创建时间
	
	@Column(name = "people_low",  nullable = false)
	private Integer peopleLow;//人数下限
	
	@Column(name = "people_high",  nullable = false)
	private Integer peopleHigh;//人数上限
	
	@Column(name = "already_num",  nullable = false)
	private Integer alreadyNum;//已报名人数
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(targetEntity=UserActivitiesRelation.class,mappedBy = "activities", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserActivitiesRelation> userActivitiesRelations;
	
	@Column(name = "status",  nullable = false)
	private Integer status;//状态：0正常，1过期，2删除
	
	@Column(name = "hot",  nullable = false)
	private Integer hot; //是否为热门活动，0否，1是

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTime() {
		return time;
	}
	
	public void setTime(Long time) {
		this.time = time;
	}
	public String getTimeStr(){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date dt = new Date(time);
		return sdf.format(dt);

	}
	public Long getCreateTime() {
		return createTime;
	}

	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getPeopleLow() {
		return peopleLow;
	}

	public void setPeopleLow(Integer peopleLow) {
		this.peopleLow = peopleLow;
	}

	public Integer getPeopleHigh() {
		return peopleHigh;
	}

	public void setPeopleHigh(Integer peopleHigh) {
		this.peopleHigh = peopleHigh;
	}

	public Integer getAlreadyNum() {
		return alreadyNum;
	}

	public void setAlreadyNum(Integer alreadyNum) {
		this.alreadyNum = alreadyNum;
	}



	public List<UserActivitiesRelation> getUserActivitiesRelations() {
		return userActivitiesRelations;
	}

	public void setUserActivitiesRelations(
			List<UserActivitiesRelation> userActivitiesRelations) {
		this.userActivitiesRelations = userActivitiesRelations;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
