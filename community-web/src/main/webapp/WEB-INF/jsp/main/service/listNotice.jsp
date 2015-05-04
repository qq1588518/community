<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../common/include_common_script.jsp"%>
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
							<li><a href="${path}/service/flow/list" >我的工作</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/notice/listNotice" class="hover">公告</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/activities/listActivities/" >活动</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/lottery/list">活动抽奖</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="get" action="${path}/service/notice/listNotice">
			<div class="cont1">
				<div class="bajy_bb">
					<div class="bajy_bb_xkgl">
						<table width="100%" border="0">
							<tr style="line-height: 50px;">
								<td valign="middle" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										搜索内容&nbsp;：&nbsp;
										<input type="text" class="xg_xtxx_srk4" name="value" value="${value}">
									</label>
								</td>
								<td valign="middle" align="left" style="vertical-align: middle;">
									开始日期&nbsp;:&nbsp;
										<input type="text" class="khgl_xl valid" style="padding-left:5px;" id="startDate" name="startDate" value="${startDate}"  onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})">
								</td>
								
								<td valign="middle" align="left" style="vertical-align: middle;">
									结束日期&nbsp;:&nbsp;
										<input type="text" class="khgl_xl valid" style="padding-left:5px;" id="endDate" name="endDate" value="${endDate}" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
								</td>
								<td>
									
									<label>
										<input type="submit" class="bajy_bb_xkgl_cxan" value="查&nbsp;找">
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<input type="button" onclick="window.location.href='${path}/service/notice/toSave'" class="bajy_bb_xkgl_cxan" value="添&nbsp;加">
									</label>
								</td>
							</tr>
						</table>
					</div>
					<div class="bajy_bb_xkgl_con fl">
						<div class="bajy_bb_xkgl_con1 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="300" height="40" align="center" valign="middle">标题</td>
									<td width="200" align="center" valign="middle">创建人</td>
									<td width="200" align="center" valign="middle">创建时间</td>
									<td align="center" valign="middle" class="right-none">操作</td>
								</tr>
							</table>
						</div>
						<div class="bajy_bb_xkgl_con2 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<c:choose>
									<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
												<c:forEach items="${page.data}" var="notice">
											<tr>
												<td width="300" height="40" align="center" valign="middle">${notice.title}</td>
												<td width="200" align="center" valign="middle">${notice.user.realName}</td>
												<td width="200" align="center" valign="middle">
												<n:dft pattern="yyyy-MM-dd" value="${notice.createTime}"/>
												</td>
												<td align="center" valign="middle" class="right-none">
													<a href="${path}/service/notice/getNotice/${notice.id}">
														<img src="${path}/images/main/sczx/xq.jpg">
													</a>
												</td>
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
								<n:paging size="10" total="${page.totalPage}" curr="${page.cpage}" href="${path}/service/notice/listNotice?value=${value}&startDate=${startDate}&endDate=${endDate}"/>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
</body>
</html>
