package com.meigu.community.view.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.meigu.community.db.pojo.Lottery;
import com.meigu.community.db.pojo.Prizes;
import com.meigu.community.db.pojo.User;
import com.meigu.community.util.common.DateUtil.Type;
import com.meigu.community.util.common.StringUtil;

@Controller
@RequestMapping("/mobile/activity")
@SuppressWarnings({"unused"})
public class MobileActivityController extends BaseController{
	
	/**活动首页*/
	@RequestMapping(value = "/index")
	public String index() {
		return "mobile/activity/index";
	}
	
	/**玩活动(抽奖)*/
	@RequestMapping(value = "/{activity}/{id}")
	public String activity(@PathVariable String activity,@PathVariable Integer  id,Model model) {
		Lottery   lottery  =null;
		try {
			Long	now		=System.currentTimeMillis();
			if (id!=null) {
				lottery = lotteryService.getLottery(id);
				if (lottery!=null) {
					Integer status		=	lottery.getStatus();
					Long	start		=	lottery.getStartDate();
					Long	end			=	lottery.getEndDate();
					if (status==0) {
						if (!(now>=start&&now<=end)) {
							lottery.setStatus(1);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("lottery", lottery);
		if (activity.equalsIgnoreCase("scratch")) {		/**刮刮乐*/
			return "mobile/activity/scratch";
		}else if (activity.equalsIgnoreCase("smash")) {	/**砸金蛋*/
			return "mobile/activity/smash";
		}else{
			return "redirect:/mobile/activity/index";
		}
	}
	
	/**
	 * <pre><b>
	 * 1.加载活动参数
	 * 2.生成用户获得的奖项
	 * </b></pre>
	 * */
	@RequestMapping(value = "/{action}")
	@ResponseBody
	public Map<String, Object> init(@PathVariable String  action,Integer  id,Model model) {
		Map<String, Object>  data=new HashMap<String, Object>();
		String 		state 		=	"ok";
		String		award		=	"继续努力哦~";		//奖品名称
		Integer		total		=	0;				//总机会数
		Integer		times		=	0;				//剩余机会数
		String		msg			=	"您今天的机会已用完,下次继续吧!";	/**您已经抽过奖了,下次继续吧*/			
		Boolean		win			=	false;			//是否中奖
		
		try {
			if (StringUtil.isNotEmpty(action)) {
				User	user	=this.getSessionUser();
				Calendar	current	= 	Calendar.getInstance();
				Long		now		=	current.getTimeInMillis();
				
				Integer		this_day	= current.get(Calendar.DAY_OF_MONTH);
				Integer		this_week	= current.get(Calendar.WEEK_OF_YEAR);
				Integer		this_month	= current.get(Calendar.MONTH);
				Integer		this_year	= current.get(Calendar.YEAR);
				if (user!=null) {
					Lottery   lottery  =lotteryService.getLottery(id);
					if (lottery!=null) {
						Integer status		=	lottery.getStatus();	//活动状态
						Integer rate 		=	lottery.getRate();		//抽奖频率
								total		=	lottery.getTimes();		//每次抽奖的机会数
						Boolean	multiple	=	lottery.getMultiple();	//每次抽奖是否可多次中奖
						Double	radix		=	lottery.getRadix();		//奖项概率基数
						Long	start		=	lottery.getStartDate();	//抽奖开始日期
						Long	end			=	lottery.getEndDate();	//抽奖结束日期
						Integer	base		=	((Long)Math.round(1/radix)).intValue();	//中奖份数
						
						if (status.equals(0)) {
							Integer playcount=0;	//抽奖次数
							Integer wincount =0;	//中奖次数
							if (now>=start&&now<=end) {
								if (action.equalsIgnoreCase("load")) {		//初始化加载参数
									if (rate.equals(1)) {		//一天一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.DAY);	//今天抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.DAY);	//今天中奖次数
									}else if (rate.equals(2)) {//一周一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.WEEK);	//这周抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.WEEK);	//这周中奖次数
									}else if (rate.equals(3)) {//一月一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.MONTH);//这月抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.MONTH);	//这月中奖次数
									}else if (rate.equals(4)) {//一年一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.YEAR);//这年抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.YEAR);	//这年中奖次数
									}else{					   //就一次
										playcount=lotteryService.playCount(user.getId(),id, now,null);//总共抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,null); //总共中奖次数
									}
									if (multiple.equals(false)) {//只有一次中奖机会
										if (wincount>=1) {
											times	=0;
											msg		="您已经抽过奖了,下次继续吧!";
										}else{
											times	=(total-playcount);
											if (times<=0) {
												msg = "您今天的机会已经用完了,下次继续吧!";
											}
										}
									}else{						//所有机会里均可中奖
										if (playcount<total) {
											times	=(total-playcount);
											if (times<=0) {
												msg = "您今天的机会已经用完了,下次继续吧!";
											}
										}else{
											times	=0;
											msg		="您今天的机会已经用完了,下次继续吧!";
										}
									}
								}else if (action.equalsIgnoreCase("fresh")) {
									Integer	value	=(new Random()).nextInt(base);	//生成奖项随机数
									if (rate.equals(1)) {		//一天一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.DAY);	//今天抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.DAY);	//今天中奖次数
									}else if (rate.equals(2)) {//一周一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.WEEK);	//这周抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.WEEK);	//这周中奖次数
									}else if (rate.equals(3)) {//一月一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.MONTH);//这月抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.MONTH);	//这月中奖次数
									}else if (rate.equals(4)) {//一年一次
										playcount=lotteryService.playCount(user.getId(),id, now,Type.YEAR);//这年抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,Type.YEAR);	//这年中奖次数
									}else{					   //就一次
										playcount=lotteryService.playCount(user.getId(),id, now,null);//总共抽奖次数
										wincount =lotteryService.winCount(user.getId(),id, now,null); //总共中奖次数
									}
									if (multiple.equals(false)) {	//只有一次中奖机会
										if (wincount>=1) {			//已经中奖了
											times	=0;
											msg		="您已经抽过奖了,下次继续吧!";
										}else{						//还没中过奖
											if (playcount<total) {
												Prizes  prizes  = lotteryService.getWinPrize(id, value);
												if (prizes!=null) {		//命中奖项
													Boolean flag=lotteryService.insertAward(lottery, prizes, user);
													if (flag.equals(true)) {
														if (prizes.getPrize().equals(0)) {//命中奖品项
															msg		=	String.format("恭喜，您抽中了:%s!",prizes.getName());
															award	=	prizes.getName();
															win		=	true;
															times	=	(times-playcount-1);
														}else{							  //未获奖
															msg		=	"遗憾,下次继续努力吧!";
															award	=	prizes.getName();
															win		=	false;
															times	=	(total-playcount-1);
														}
													}else{
														msg		=	"遗憾,下次继续努力吧!";
														award	=	prizes.getName();
														win		=	false;
														times	=	(total-playcount);
													}
												}else{				//未命中奖项
													prizes  = lotteryService.getNonPrize(id);//加载非奖品奖项
													if (prizes!=null) {
														Boolean flag=lotteryService.insertAward(lottery, prizes, user);
														if (flag.equals(true)) {
															msg		=	"遗憾,下次继续努力吧!";
															award	=	prizes.getName();
															win		=	false;
															times	=	(total-playcount);
														}else{
															msg		=	"遗憾,下次继续努力吧!";
															award	=	"继续努力喔~";
															win		=	false;
															times	=	(total-playcount);
														}
													}else{
														msg		=	"遗憾,下次继续努力吧!";
														award	=	"继续努力喔~";
														win		=	false;
														times	=	(total-playcount);
													}
												}
											}else{
												award	=	"继续努力喔~";
												win		=	false;
												times	=0;
												msg		="您今天的机会已经用完了,下次继续吧!";
											}
										}
									}else{						//所有机会里均可中奖
										if (playcount<total) {	//抽奖机会未用完
											Prizes  prizes  = lotteryService.getWinPrize(id, value);
											if (prizes!=null) {		//命中奖项
												Boolean flag=lotteryService.insertAward(lottery, prizes, user);
												if (flag.equals(true)) {
													if (prizes.getPrize().equals(0)) {//命中奖品项
														msg		=	String.format("恭喜，您抽中了:%s!",prizes.getName());
														award	=	prizes.getName();
														win		=	true;
														times	=	(times-playcount-1);
													}else{							  //未获奖
														msg		=	"遗憾,下次继续努力吧!";
														award	=	prizes.getName();
														win		=	false;
														times	=	(total-playcount-1);
													}
												}else{
													msg		=	"遗憾,下次继续努力吧!";
													award	=	prizes.getName();
													win		=	false;
													times	=	(total-playcount);
												}
											}else{				//未命中奖项
												prizes  = lotteryService.getNonPrize(id);//加载非奖品奖项
												if (prizes!=null) {
													Boolean flag=lotteryService.insertAward(lottery, prizes, user);
													if (flag.equals(true)) {
														msg		=	"遗憾,下次继续努力吧!";
														award	=	prizes.getName();
														win		=	false;
														times	=	(total-playcount);
													}else{
														msg		=	"遗憾,下次继续努力吧!";
														award	=	"继续努力喔~";
														win		=	false;
														times	=	(total-playcount);
													}
												}else{
													msg		=	"遗憾,下次继续努力吧!";
													award	=	"继续努力喔~";
													win		=	false;
													times	=	(total-playcount);
												}
											}
										}else{					//抽奖机会已用完
											times	=0;
											msg		="您今天的机会已经用完了,下次继续吧!";
										}
									}
								}else{
									state 	="error";
									msg		="未知操作!";
								}
							}else{
								state 	="error";
								msg		="活动已过期!";
							}
						}else{
							state 	="error";
							msg		="活动已过期!";
						}
					}else{
						state 	="error";
						msg		="活动不存在!";
					}
				}else{
					state 	="error";
					msg		="请先登录!";
				}
			}else{
				state 	="error";
				msg		="加载失败!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			state 	="error";
			msg		="加载失败!";
		}
		data.put("total", total);
		data.put("times", times);
		data.put("award", award);
		data.put("msg", msg);
		data.put("win", win);
		
		root.put("state", state);
		root.put("data", data);
		return root;
	}
}
