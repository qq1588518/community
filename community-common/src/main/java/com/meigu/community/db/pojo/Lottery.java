package com.meigu.community.db.pojo;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.*;
import com.meigu.community.util.common.*;

/**
 *  抽奖活动
 */
@Entity
@Table(name = "lottery")
public class Lottery  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false,updatable=false)
	private			Integer			id;			//id编号
	
	@Column(name = "name", nullable = false)
	private			String			name;		//抽奖名称
	
	@Column(name = "rate", nullable = false)
	private			Integer			rate;		//抽奖频率 1：每天,2：每周,3：每月,4：每年,5：一次性(就一次)
	
	@Column(name = "times", nullable = false)
	private			Integer			times		=1;		//每次抽奖的机会数
	
	@Column(name = "multiple", nullable = false)
	private			Boolean			multiple	=false;	//每次抽奖是否可多次中奖(0(false):否,1(true):是)
	
	@Column(name = "radix", nullable = false)
	private			Double			radix;		//概率(奖项概率基数;如:0.01,0.001,0.0001)
	
	@Column(name = "start_date", nullable = false)
	private			Long			startDate;		//抽奖开始日期
	
	@Column(name = "end_date", nullable = false)
	private			Long			endDate;		//抽奖结束日期
	
	@Column(name = "status", nullable = false)
	private			Integer			status		=0;	//抽奖活动状态(0:正在进行中,1:已过期)
	
	
	@Column(name = "descr", nullable = true)
	private			String			descr;			//活动描述
	
	@Column(name = "create_time", nullable = false,updatable=false)
	private			Long			createDate;		//添加时间
	
	@OneToMany(targetEntity=Prizes.class,mappedBy="lottery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Where(clause="status=0")
	@OrderBy(value="rank ASC")
	private			List<Prizes>		prizelist;
	
	@Transient
	private			String				start_Date;
	@Transient
	private			String				end_Date;

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


	public Integer getRate() {
		return rate;
	}


	public void setRate(Integer rate) {
		this.rate = rate;
	}


	public Integer getTimes() {
		return times;
	}


	public void setTimes(Integer times) {
		this.times = times;
	}


	public Boolean getMultiple() {
		return multiple;
	}


	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}


	public Double getRadix() {
		return radix;
	}


	public void setRadix(Double radix) {
		this.radix = radix;
	}

	public Long getStartDate() {
		return startDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public Long getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}


	public List<Prizes> getPrizelist() {
		return prizelist;
	}
	public void setPrizelist(List<Prizes> prizelist) {
		this.prizelist = prizelist;
	}

	public String getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(String start_Date) {
		if(StringUtil.isNotEmpty(start_Date)){
			try {
				this.startDate=DateUtil.parseDate(start_Date).getTime();
			} catch (Exception e) {}
		}
		this.start_Date = start_Date;
	}


	public String getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(String end_Date) {
		if(StringUtil.isNotEmpty(end_Date)){
			try {
				this.endDate=DateUtil.parseDate(end_Date).getTime();
			} catch (Exception e) {}
		}
		this.end_Date = end_Date;
	}
	
}
