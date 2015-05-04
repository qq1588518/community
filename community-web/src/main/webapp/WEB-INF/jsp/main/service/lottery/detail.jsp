<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/main/flowdetail.css">
    <%@include file="../../../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/main/examine.js"></script>
  </head>

<body>
	<div class="content">
		<div class="top">
			<div class="top1">
				<div class="top1_t">
					<div class="top1_tleft fl">
						<p class="logo3">
							<a href="${path}/manage/logout"><spring:message code="project.name"/></a>
						</p>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/flow/list">我的工作</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/notice/listNotice">公告</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/activities/listActivities">活动</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/lottery/list" class="hover">活动抽奖</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<div class="cont1">
			<div class="xg_zhsz">
				<c:choose>
					<c:when test="${!empty lottery}">
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt">&nbsp;&nbsp;基本资料&nbsp;&nbsp;</p>
							<div class="zxxg_zhsz1">
								<table id="lottery_term" width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center" valign="middle">
										<td width="102" height="43" valign="middle" align="right">活动名称： </td>
										<td width="310" valign="middle" colspan="2" align="left">
											<label>
												${lottery.name}
											</label>
										</td>
										<td height="43" width="102" valign="middle" align="right"> 参与频率： </td>
										<td width="310" colspan="2" valign="middle" align="left">
											<label>
												<n:lotteryrate key="${lottery.rate}"/>
											</label>
										</td>
									</tr>
									<tr align="center" valign="middle">
										<td width="102" height="43" valign="middle" align="right">开始时间： </td>
										<td width="310" valign="middle" colspan="2" align="left">
											<label>
												<n:dft pattern="yyyy-MM-dd" value="${lottery.startDate}"/>
											</label>
										</td>
										<td height="43" width="102" valign="middle" align="right">结束时间： </td>
										<td width="310" colspan="2" valign="middle" align="left">
											<label>
												<n:dft pattern="yyyy-MM-dd" value="${lottery.endDate}"/>
											</label>
										</td>
									</tr>
									<tr align="center" valign="middle">
										<td width="102" height="43" valign="middle" align="right">概率基数： </td>
										<td width="310" valign="middle" colspan="2" align="left">
											<label>
												<fmt:formatNumber pattern="0.#######" value="${lottery.radix}"/>
											</label>
										</td>
										<td height="43" width="102" valign="middle" align="right">活动状态： </td>
										<td width="310" colspan="2" valign="middle" align="left">
											<label>
												${(lottery.status eq 0)?'进行中':'已结束'}
											</label>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt" style="margin-top: 0px;">
								&nbsp;&nbsp;奖品项&nbsp;&nbsp;
							</p>
							<div class="zxxg_zhsz1">
								<table id="prizes_term" width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr height="35px;" align="center" valign="middle" style="border-right: 1px dashed #cbcbcb;background:#f2f2f2;">
										<td valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">奖项名称</td>
										<td width="200" valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">中奖概率</td>
										<td width="60" 	valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">是否奖品</td>
										<td width="60" 	valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">排序</td>
										<td width="100" valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">操作</td>
									</tr>
									<c:forEach items="${lottery.prizelist}" var="prize">
										<tr height="35px;" align="center" valign="middle" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
											<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">
												${prize.name}
											</td>
											<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
												<fmt:formatNumber pattern="0.#######" value="${prize.chance}"/>
											</td>
											<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
												${(prize.prize eq 0)?'是':'否'}
											</td>
											<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
												${prize.rank}
											</td>
											<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
												&nbsp;
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="zxxg_zhsz fl">
							<div class="zxxg_zhsz1" style="padding-top:10px;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center" valign="middle">
										<td valign="middle" align="right" width="80px">活动描述： </td>
										<td valign="middle" align="left" colspan="3">
											<label>
												${lottery.descr}
											</label>
										</td>
									</tr>
									<tr align="center" valign="middle" style="height:60px;line-height: 60px;">
										<td  valign="middle" align="center" colspan="4">
											<a style="display: block;color: white;" href="${path}/service/lottery/list?type=activity" class="bajy_bb_xkgl_btn_black">返&nbsp;&nbsp;回</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt">&nbsp;&nbsp;基本资料&nbsp;&nbsp;</p>
							<div class="zxxg_zhsz1">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center" valign="middle" style="height:60px;line-height: 60px;">
										<td>
											抱歉，您要查看的项目不存在!
										</td>
									</tr>
									<tr align="center" valign="middle" style="height:60px;line-height: 60px;">
										<td>
											<a style="display: block;color: white;" href="${referer}" class="bajy_bb_xkgl_btn_black">返&nbsp;&nbsp;回</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!--cont end-->
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
</body>
</html>