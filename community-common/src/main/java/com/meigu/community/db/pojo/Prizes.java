package com.meigu.community.db.pojo;

import java.io.*;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 *  奖品设置项
 */
@Entity
@Table(name = "prizes")
public class Prizes  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false,updatable=false)
	private			Integer			id;				//id编号

	@Column(name = "name",  nullable = false)
	private			String			name;			//奖品名称
	
	@Column(name = "region_start",  nullable = false)
	private			Integer			regionStart;	//中奖值区间(开始)
	
	@Column(name = "region_end",  nullable = false)
	private			Integer			regionEnd;		//中奖值区间(结束)
	
	@Column(name = "rank",  nullable = false)
	private			Integer			rank;			//奖项排序
	
	@Column(name = "chance",  nullable = false)
	private			Double			chance;			//奖项概率
	
	@Column(name = "prize",  nullable = false, columnDefinition="int default 0")
	private			Integer			prize		=0;	//是否属于奖品项(0:是,1:否)
	
	@Column(name = "status",  nullable = false, columnDefinition="int default 0")
	private			Integer			status		=0;	//奖品状态(0:生效,1:失效)
	
	@ManyToOne(targetEntity = Lottery.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="l_id")
	private			Lottery			lottery;		//所属抽奖活动


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


	public Integer getRegionStart() {
		return regionStart;
	}
	public void setRegionStart(Integer regionStart) {
		this.regionStart = regionStart;
	}


	public Integer getRegionEnd() {
		return regionEnd;
	}
	public void setRegionEnd(Integer regionEnd) {
		this.regionEnd = regionEnd;
	}


	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}


	public Double getChance() {
		return chance;
	}
	public void setChance(Double chance) {
		this.chance = chance;
	}


	public Integer getPrize() {
		return prize;
	}
	public void setPrize(Integer prize) {
		this.prize = prize;
	}


	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	public Lottery getLottery() {
		return lottery;
	}
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}
	
}
