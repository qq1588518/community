<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="activity.smash"/></title>
	<%@include file="../../../common/include_app_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/mobile/common.css">
	<link rel="stylesheet" type="text/css" href="${path}/css/mobile/index.css">
	<link rel="stylesheet" href="${path}/js/common/alert/style.css" type="text/css"/>
	<script type="text/javascript">var _path="${path}";</script>
	<script type="text/javascript" src="${path}/js/plugins/jquery/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${path}/js/common/alert/alert.js"></script>
	<style type="text/css">
		.patch,.flower{
			display: none;
		}
	</style>
	<script type="text/javascript">
		var lotteryTime	=0; 				//剩余抽奖次数
		var msg			="您今天的机会已经用完了,下次继续吧!";		//抽奖完成提示消息
		var load		=false;		//是否已加载奖项
		var lock		=false;		//抽奖活动是否锁定
		var prize		="继续努力喔~";//奖项
		var win			=false;		//奖品是否中奖
		
		function poundEgg(obj){
			var _this=obj;
			$.ajax({//加载奖品数据
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
						if(win){
							$(_this).children("span").hide();
							$(_this).children("div.normal").hide(0);
							$(_this).children("div.flower").fadeIn("slow");
							$(_this).addClass("broke");
							$("p.hammer").hide(0);
						}else{
							$(_this).children("span").hide();
							$(_this).children("div.normal").hide(0);
							$(_this).children("div.patch").fadeIn("slow");
							$(_this).addClass("broke");
							$("p.hammer").hide(0);
						}
						var len=$("div.hit_eggs li[class=broke]").length;
						if(len>=3){
							tooltip(msg,function(){
								window.location.reload();
							});
						}else{
							tooltip(msg);
						}
					}else{
						var obj=item.data;
						msg		=obj["msg"];
						win=false;
					}
				}
			});
		}
		$(function(){
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
			
			$("div.hit_eggs li").each(function(){
				$(this).hover(function(){
					var posL = $(this).position().left + $(this).width();
					$("p.hammer").show().css('left', posL);
				});
				$(this).click(function(){
					if($(this).hasClass("broke")){
						tooltip("您已经砸过了!");
					}else{
						poundEgg(this);
					}
				});
			});
		});
	</script>
  </head>

<body>
	<div class="Overall_situation">
		<img class="common_pic" src="${path}/images/mobile/hit_eggs.png" />
        <div class="hit_eggs">
        	<ul>
	            <p class="hammer">
	            	<img width="60%" height="50%" src="${path}/images/mobile/hammer.png" />
	            </p>
            	<li>
            		<div class="normal"><img width="85%" height="100%" src="${path}/images/mobile/eggs/egg.png"/></div>
            		<div class="patch"><img width="85%" height="100%" src="${path}/images/mobile/eggs/patch.png"/></div>
            		<div class="flower"><img width="85%" height="90%" src="${path}/images/mobile/eggs_img.png"/></div>
            		<span>1</span>
            	</li>
                <li>
                	<div class="normal"><img width="85%" height="100%" src="${path}/images/mobile/eggs/egg.png"/></div>
                	<div class="patch"><img width="85%" height="100%" src="${path}/images/mobile/eggs/patch.png"/></div>
            		<div class="flower"><img width="85%" height="90%" src="${path}/images/mobile/eggs_img.png"/></div>
                	<span>2</span>
                </li>
                <li>
                	<div class="normal"><img width="85%" height="100%" src="${path}/images/mobile/eggs/egg.png"/></div>
                	<div class="patch"><img width="85%" height="100%" src="${path}/images/mobile/eggs/patch.png"/></div>
            		<div class="flower"><img width="85%" height="90%" src="${path}/images/mobile/eggs_img.png"/></div>
                	<span>3</span>
                </li>
            </ul>
        </div>
	</div>
</body>
</html>
<!-- 
	<li><img width="100%" height="100%" src="${path}/images/mobile/eggs_img.png"/></li>
                <li><img width="88%" height="100%" src="${path}/images/mobile/egg_2.png" /></li>
                <li><img width="75%" height="100%" src="${path}/images/mobile/egg3.png" /></li>
 -->
