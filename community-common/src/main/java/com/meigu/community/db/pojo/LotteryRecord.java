package com.meigu.community.db.pojo;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;


/**
 *  抽奖记录表
 */
@Entity
@Table(name = "lottery_record")
public class LotteryRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false,updatable=false)
	private		Integer			id;
	
	@Column(name = "create_time", nullable = false,updatable=false)
	private		Long			createDate;		//抽奖时间
	
	@ManyToOne(targetEntity = Lottery.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="l_id")
	private			Lottery			lottery;		//所属抽奖活动
	
	@ManyToOne(targetEntity = Prizes.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="p_id")
	private			Prizes			prizes;			//所中奖项
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private			User			user;			//抽奖用户

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Lottery getLottery() {
		return lottery;
	}
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	public Prizes getPrizes() {
		return prizes;
	}
	public void setPrizes(Prizes prizes) {
		this.prizes = prizes;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
