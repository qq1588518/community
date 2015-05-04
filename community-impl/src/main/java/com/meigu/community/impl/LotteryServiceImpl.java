package com.meigu.community.impl;

import java.util.*;
import org.springframework.stereotype.*;
import com.meigu.community.dao.*;
import com.meigu.community.db.pojo.*;
import com.meigu.community.service.*;
import com.meigu.community.util.common.DateUtil.Type;
import com.meigu.community.util.common.StringUtil;
import com.meigu.community.util.persistence.model.Paginate;

import org.springframework.beans.factory.annotation.*;


@Repository
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private		LotteryDao		lotteryDao;
	
	
	@Override
	public Boolean updateLottery(Lottery  lottery) throws Exception {
		Boolean flag=false;
		try {
			if (lottery!=null&&lottery.getId()!=null&&lottery.getTimes()>=1&&(lottery.getPrizelist()!=null&&lottery.getPrizelist().size()>1)) {
				Lottery temp = this.getLottery(lottery.getId());
				if (!StringUtil.isNotEmpty(lottery.getName())) {
					lottery.setName(temp.getName());
				}
				if (lottery.getRate()==null) {
					lottery.setRate(temp.getRate());
				}
				if (lottery.getTimes()==null) {
					lottery.setTimes(temp.getTimes());
				}
				if (lottery.getMultiple()==null) {
					lottery.setMultiple(temp.getMultiple());
				}
				if (lottery.getRadix()==null) {
					lottery.setRadix(temp.getRadix());
				}
				if (lottery.getStartDate()==null) {
					lottery.setStartDate(temp.getStartDate());
				}
				if (lottery.getEndDate()==null) {
					lottery.setEndDate(temp.getEndDate());
				}
				if (lottery.getStatus()==null) {
					lottery.setStatus(temp.getStatus());
				}
				if (!StringUtil.isNotEmpty(lottery.getDescr())) {
					lottery.setDescr(temp.getDescr());
				}
				lottery.setCreateDate(temp.getCreateDate());
				
				Double	radix 	= 	lottery.getRadix();
				Integer	base	=	0;
				if (radix>0) {
					base=radix.intValue();
					radix=(1.0/radix);
					lottery.setRadix(radix);
				}else{
					base=((Long)Math.round(1/radix)).intValue();	//中奖份数
				}
				List<Prizes> prizelist = lottery.getPrizelist();
				Collections.sort(prizelist, new Comparator<Prizes>() {
					@Override
					public int compare(Prizes s, Prizes t) {
						return s.getChance().compareTo(t.getChance());
					}
				});
				Integer start = 0,end=0;
				for (Prizes prize:prizelist) {
					Double  odds  = (prize.getChance()*radix);
					Integer	range = ((Long)Math.round(base*odds)).intValue();
					end=end+range;
					prize.setLottery(lottery);
					prize.setChance(odds);
					prize.setRegionStart(start);
					prize.setRegionEnd(end);
					start 	= end+1;
					end		= start+range-1;
				}
				lottery.setPrizelist(prizelist);
				flag=lotteryDao.updateLottery(lottery);
			}else{
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
			throw e;
		}
		return flag;
	}
	
	@Override
	public Boolean insertLottery(Lottery  lottery) throws Exception{
		Boolean flag=false;
		try {
			Long	now  = System.currentTimeMillis();
			if (lottery!=null&&lottery.getTimes()>=1&&(lottery.getPrizelist()!=null&&lottery.getPrizelist().size()>1)) {
				lottery.setCreateDate(now);
				lottery.setStatus(0);
				Double	radix 	= 	lottery.getRadix();
				Integer	base	=	0;
				if (radix>0) {
					base=radix.intValue();
					radix=(1.0/radix);
					lottery.setRadix(radix);
				}else{
					base=((Long)Math.round(1/radix)).intValue();	//中奖份数
				}
				List<Prizes> prizelist = lottery.getPrizelist();
				Collections.sort(prizelist, new Comparator<Prizes>() {
					@Override
					public int compare(Prizes s, Prizes t) {
						return s.getChance().compareTo(t.getChance());
					}
				});
				Integer start = 0,end=0;
				for (Prizes prize:prizelist) {
					Double  odds  = (prize.getChance()*radix);
					Integer	range = ((Long)Math.round(base*odds)).intValue();
					end=end+range;
					prize.setLottery(lottery);
					prize.setChance(odds);
					prize.setRegionStart(start);
					prize.setRegionEnd(end);
					prize.setStatus(0);
					start 	= end+1;
					end		= start+range-1;
				}
				flag=lotteryDao.insertLottery(lottery);
			}else{
				flag=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return flag;
	}
	
	@Override
	public Lottery getLottery(Integer id) {
		return lotteryDao.getLottery(id);
	}

	@Override
	public Prizes getPrizes(Integer id) {
		return lotteryDao.getPrizes(id);
	}

	/**抽奖活动列表*/
	@Override
	public Paginate lotterylist(String keyword,Integer rate,
			Long startDate,Long endDate,Integer status,
			Integer cpage,Integer pageSize) throws Throwable{
		return lotteryDao.lotterylist(keyword, rate, startDate, endDate, status, cpage, pageSize);
	}
	
	@Override
	public Paginate awardlist(String keyword,Long startDate,Long endDate,Integer cpage,Integer pageSize){
		return lotteryDao.awardlist(keyword, startDate, endDate, cpage, pageSize);
	}
	
	@Override
	public	Prizes	getWinPrize(Integer lotteryId,Integer value){
		return lotteryDao.getWinPrize(lotteryId, value);
	}
	
	@Override
	/**获取当前非奖品奖项*/
	public	Prizes	getNonPrize(Integer lotteryId){
		return lotteryDao.getNonPrize(lotteryId);
	}
	
	/**添加抽奖记录*/
	@Override
	public Boolean insertAward(Lottery lottery,Prizes prizes,User user) throws Exception{
		return lotteryDao.insertAward(lottery, prizes, user);
	}
	
	@Override
	public List<Prizes> prizeslisList(Integer lotteryId) throws Exception{
		return lotteryDao.prizeslisList(lotteryId);
	}

	/**指定日期内抽奖次数*/
	public Integer	playCount(String userId,Integer lotteryId,Long date,Type type){
		return lotteryDao.playCount(userId, lotteryId, date,type);
	}
	/**指定日期内中奖次数*/
	public Integer	winCount(String userId,Integer lotteryId,Long date,Type type){
		return lotteryDao.winCount(userId, lotteryId, date,type);
	}
}
