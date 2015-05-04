package com.meigu.community.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.meigu.community.db.pojo.*;
import com.meigu.community.util.common.DateUtil.Type;
import com.meigu.community.util.persistence.model.Paginate;


@Service
public interface LotteryService {
	
	/**添加抽奖活动(包括设置的奖项)*/
	public Boolean insertLottery(Lottery  lottery) throws Exception;
	
	/**更新抽奖活动(包括设置的奖项)*/
	public Boolean updateLottery(Lottery  lottery) throws Exception;
	
	public 	Lottery 	getLottery(Integer id);
	
	public 	Prizes 	getPrizes(Integer id);
	
	/**抽奖活动列表*/
	public Paginate lotterylist(String keyword,Integer rate,
			Long startDate,Long endDate,Integer status,
			Integer cpage,Integer pageSize) throws Throwable;
	
	/**奖品列表*/
	public Paginate awardlist(String keyword,Long startDate,Long endDate,Integer cpage,Integer pageSize);
	
	/**
	 * 获取当前中奖的奖项
	 * @param lotteryId  抽奖的活动Id
	 * @param value		 用户抽奖产生的值
	 * @return 奖项
	 */
	public	Prizes	getWinPrize(Integer lotteryId,Integer value);
	/**获取当前非奖品奖项*/
	public	Prizes	getNonPrize(Integer lotteryId);
	
	/**添加抽奖记录*/
	public Boolean insertAward(Lottery lottery,Prizes prizes,User user) throws Exception;
	
	/**获取所有有效奖项
	 * @throws Exception */
	public List<Prizes> prizeslisList(Integer lotteryId) throws Exception;
	
	/**指定日期内抽奖次数*/
	public Integer	playCount(String userId,Integer lotteryId,Long date,Type type);
	/**指定日期内中奖次数*/
	public Integer	winCount(String userId,Integer lotteryId,Long date,Type type);
}
