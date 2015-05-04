<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../../common/include_common_script.jsp"%>
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
		<form id="form1" name="form1" method="get" action="${path}/service/lottery/list">
			<div class="cont1">
				<div class="bajy_bb">
					<div class="bajy_bb_xkgl">
						<table width="100%"  border="0">
							<tr style="line-height: 50px;">
								<td valign="middle" width="230px;" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										抽奖名称&nbsp;:&nbsp;
										<input type="text" class="xg_xtxx_srk4" name="keyword" value="${keyword}">
									</label>
								</td>
								<td valign="middle" width="170px;" align="left" style="vertical-align: middle;">
									<label>
										开始日期&nbsp;:&nbsp;
										<input type="text" readonly="readonly" class="khgl_xl valid" style="padding-left:5px;" id="startDate" name="startDate" value="${startDate}" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1})}'});">
									</label>
								</td>
								<td valign="middle" width="170px;" align="left" style="vertical-align: middle;">
									<label>
										结束日期&nbsp;:&nbsp;
										<input type="text" readonly="readonly" class="khgl_xl valid" style="padding-left:5px;" id="endDate" name="endDate" value="${endDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:1})}'});">
									</label>
								</td>
								<td valign="middle" align="center" style="vertical-align: middle;">
									<label>
										<input type="hidden" name="type" value="award"/>
										<input type="submit" class="bajy_bb_xkgl_cxan" style="margin-top:10px;" value="查&nbsp;找">
									</label>
								</td>
							</tr>
						</table>
					</div>
					<div class="sczx_bq fl">
						<ul>
							<li><a href="${path}/service/lottery/list?type=activity">活动列表</a></li>
							<li class="hover"><a href="${path}/service/lottery/list?type=award">中奖列表</a></li>
						</ul>
					</div>
					<div class="bajy_bb_xkgl_con fl">
						<div class="bajy_bb_xkgl_con1 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center" valign="middle" height="40">所属活动</td>
									<td width="80" align="center" valign="middle">参与频率</td>
									<td width="90" align="center" valign="middle">开始日期</td>
									<td width="90" align="center" valign="middle">结束日期</td>
									<td width="80" align="center" valign="middle">活动状态</td>
									<td width="140" align="center" valign="middle">奖品名称</td>
									<td width="80" align="center" valign="middle">中奖概率</td>
									<td width="120" align="center" valign="middle">中奖时间</td>
									<td width="120" align="center" valign="middle">中奖用户</td>
									<td width="100" align="center" valign="middle" class="right-none">用户电话</td>
								</tr>
							</table>
						</div>
						<div class="bajy_bb_xkgl_con2 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<c:choose>
									<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
										<c:forEach items="${page.data}" var="award">
											<tr>
												<td height="40" align="center" valign="middle">${award.lotteryName}</td>
												<td width="80"  align="center" valign="middle"><n:lotteryrate key="${award.rate}"/></td>
												<td width="90" align="center" valign="middle"><n:dft pattern="yyyy-MM-dd" value="${award.startDate}"/></td>
												<td width="90" align="center" valign="middle"><n:dft pattern="yyyy-MM-dd" value="${award.endDate}"/></td>
												<td width="80" align="center" valign="middle">${(award.lotteryStatus eq 0)?'进行中':'已过期'}</td>
												<td width="140" align="center" valign="middle">${award.awardName}</td>
												<td width="80" align="center" valign="middle"><fmt:formatNumber pattern="0.#######" value="${award.chance}"/></td>
												<td width="120" align="center" valign="middle"><n:dft pattern="yyyy-MM-dd HH:mm" value="${award.createDate}"/></td>
												<td width="120" align="center" valign="middle">${award.username}(${award.realName})</td>
												<td width="100" align="center" valign="middle" class="right-none">${award.mobilePhone}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="7" height="40" align="center" valign="middle">暂无数据...</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<div class="wdfy_cont_foot fl">
						<div class="page">
							<c:if test="${(!empty page)}">
								<n:paging size="10" total="${page.totalPage}" curr="${page.cpage}" href="${path}/service/lottery/list??type=award&startDate=${startDate}&endDate=${endDate}&keyword=${keyword}"/>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--cont end-->
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
</body>
</html>
