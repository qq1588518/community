package com.meigu.community.vo;

import java.io.*;
import java.math.BigInteger;

/**
 *  奖项明细
 */
public class LotteryRecordBrief  implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private			Integer			id;
	
	private			Integer			lotteryId;			//抽奖活动ID
		
	private			String			lotteryName;		//抽奖活动名称
	
	private			Integer			rate;				//抽奖频率 1：每天,2：每周,3：每月,4：每年,5：一次性(就一次)
	
	private			Integer			times		=1;		//每次抽奖的机会数
	
	private			Boolean			multiple	=false;	//每次抽奖是否可多次中奖(0(false):否,1(true):是)
	
	private			Double			radix;				//概率(奖项概率基数;如:0.01,0.001,0.0001)
	
	private			Long			startDate;			//抽奖开始日期
	
	private			Long			endDate;			//抽奖结束日期
	
	private			Integer			lotteryStatus	=0; //抽奖活动状态
	
	private			Integer			prizeId;			//奖品id编号

	private			String			awardName;			//奖品名称
	
	private			Integer			regionStart;		//中奖值区间(开始)
	
	private			Integer			regionEnd;			//中奖值区间(结束)
	
	private			Integer			rank;				//奖项排序
	
	private			Double			chance;				//奖项概率
	
	private			Integer			prize			=0;	//是否属于奖品项(0:是,1:否)
	
	private			Integer			prizeStatus		=0; //奖品状态
	
	private			Long			createDate;			//中奖时间
	
	private			String			userId;				//中奖用户Id
	private			String			username;			//中奖用户用户名
	private			String			realName;			//中奖用户真实姓名
	private			String			mobilePhone;		//中奖用户联系电话
	
	public LotteryRecordBrief() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Integer lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getLotteryName() {
		return lotteryName;
	}

	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
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

	public void setMultiple(Integer multiple) {
		if (multiple!=null) {
			if (multiple.equals(0)) {
				this.multiple=false;
			}else{
				this.multiple=true;
			}
		}else{
			this.multiple = false;
		}
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

	public void setStartDate(BigInteger startDate) {
		if (startDate!=null) {
			this.startDate = startDate.longValue();
		}
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(BigInteger endDate) {
		if (endDate!=null) {
			this.endDate = endDate.longValue();
		}
	}

	public Integer getLotteryStatus() {
		return lotteryStatus;
	}

	public void setLotteryStatus(Integer lotteryStatus) {
		this.lotteryStatus = lotteryStatus;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
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

	public Integer getPrizeStatus() {
		return prizeStatus;
	}

	public void setPrizeStatus(Integer prizeStatus) {
		this.prizeStatus = prizeStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(BigInteger createDate) {
		if (createDate!=null) {
			this.createDate = createDate.longValue();
		}
	}
	
}
