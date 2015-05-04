<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="activity.scratch"/></title>
	<%@include file="../../../common/include_app_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/mobile/common.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/mobile/index.css"/>
	<link rel="stylesheet" href="${path}/js/common/alert/style.css" type="text/css"/>
	<script type="text/javascript" src="${path}/js/plugins/jquery/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${path}/js/common/alert/alert.js"></script>
	<script type="text/javascript" src="${path}/js/plugins/wScratchPad.js"></script>
	<style type="text/css">
		#front, #back{
            border-radius: 5px;
        }
	</style>
	<script type="text/javascript">
		var lotteryTime	=0; 				//剩余抽奖次数
		var msg			="您今天的机会已经用完了,下次继续吧!";		//抽奖完成提示消息
		var load		=false;		//是否已加载奖项
		var lock		=false;		//抽奖活动是否锁定
		var prize		="继续努力喔~";//奖项
		var win			=false;		//奖品是否中奖
		
		function loadData(){		//加载用户初始化数据
			$.ajax({
				url:"${path}/mobile/activity/load?id=${lottery.id}",
				type:'post',
				dataType:'json',
				success:function(item){
					load=false;
					var obj=item.data;
					lotteryTime	=obj["times"];
					msg			=obj["msg"];
					lock		=false;
				},error:function(item){
					load=false;
					msg="活动加载失败!";
				}
			});
		}
		function lottery(){			//加载奖品数据
			$.ajax({
				url:"${path}/mobile/activity/fresh?id=${lottery.id}",
				type:"post",
				dataType:"json",
				success:function(item){
					load =true;
					if(item.state=='ok'){
						var obj=item.data;
						lotteryTime=obj["times"];
						win		=obj["win"];
						prize		=obj["award"];
						msg		=obj["msg"];
					}else{
						var obj=item.data;
						msg		=obj["msg"];
						win=false;
					}
					$("#back").html(prize);
				}
			});
		}
		function init(){
			var h=$("div.ggl_gl_pic").height();
			var w=$("div.ggl_gl_pic").width();
			var num=0;
			$("#front").wScratchPad({
				width: w,
				height:h,
				color:"#A3A4A8",
				cursor:"${path}/images/mobile/coin.png",
				scratchMove: function(e, percent) {
					num++;
					if(lotteryTime>0){
						if(load==false&&num==1){
							lottery();
						}
						if(percent>=30){
							$("#front").wScratchPad('enable',false);
							showAward();
						}
					}else{
						showAward();
					}
				}
			});
			loadData();
		}
		function showAward(){
			if(lock==false){
				tooltip(msg,function(){
					window.location.reload();
				});
				load=false;
				lock=true;
			}
		}
		$(window).bind("ready scroll resize",function(){
			$("#front").html("");
			init();
		});
	</script>
  </head>

<body>
	<div class="Overall_situation">
		<c:choose>
			<c:when test="${(!empty lottery)&&(lottery.status eq 0)}">
				<img class="common_pic" src="${path}/images/mobile/big_bg.png" />
				<div id="back" class="ggl_gl_pic_gd"></div><!--您已抽过奖了!  亲，您今日的机会已用完了，明日再来吧! -->
				<div id="front" class="ggl_gl_pic"></div>
				<div class="ggl_gl_text">
					小提示：<br/>
					1.活动时间:<n:dft pattern="yyyy/MM/dd" value="${lottery.startDate}"/> ~ <n:dft pattern="yyyy/MM/dd" value="${lottery.endDate}"/><br/>
					2.介绍:${lottery.descr}
				</div>
			</c:when>
			<c:otherwise>
				<img class="common_pic" src="${path}/images/mobile/big_bg.png" />
				<div id="back" class="ggl_gl_pic_gd">活动不存在或已过期!</div>
				<div class="ggl_gl_text">
					小提示：<br/>
					每天登录有1张刮刮卡哦~,可抽神秘大奖！
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>