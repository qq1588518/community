<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../../common/include_common_script.jsp"%>
	<script type="text/javascript">
		$(function(){
			$("#flowType").change(function(){
				var val=$(this).find("option:selected").val();
				if(val==undefined||val==''){
					$("#flowStatus").attr("disabled","disabled");
				}else{
					$("#flowStatus").removeAttr("disabled");
				}
			});
		});
	</script>
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
							<li><a href="${path}/service/flow/list" class="hover">我的工作</a></li>
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
							<li><a href="${path}/service/lottery/list">活动抽奖</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="get" action="${path}/service/flow/list">
			<div class="cont1">
				<div class="bajy_bb">
					<div class="bajy_bb_xkgl">
						<table width="100%" border="0">
							<tr style="line-height: 50px;">
								<td valign="middle" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										用户姓名&nbsp;：&nbsp;
										<input type="text" class="xg_xtxx_srk4" name="keyword" value="${keyword}">
									</label>
								</td>
								<td valign="middle" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										工作类别&nbsp;：&nbsp;
										<select class="khgl_xl valid" id="flowType" name="type">
											<option value="" ${(empty type)?'selected=selected':''}>待办事项</option>
											<c:if test="${(!empty flowMainList)&&(fn:length(flowMainList)>0)}">
												<c:forEach items="${flowMainList}" var="flowType">
													<option value="${flowType.id}" ${(type eq (flowType.id))?'selected=selected':''}>${flowType.name}</option>
												</c:forEach>
											</c:if>
										</select>
									</label>
								</td>
								<td valign="middle" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										状态&nbsp;：&nbsp;
										<select class="khgl_xl valid" ${(empty type)?'disabled=disabled':''} id="flowStatus" name="status">
											<option value=""  ${(empty status)?'selected=selected':''}>全部</option>
											<option value="1" ${(status eq 1)?'selected=selected':''}><n:flowstatus key="1"/></option>
											<option value="2" ${(status eq 2)?'selected=selected':''}><n:flowstatus key="2"/></option>
											<option value="3" ${(status eq 3)?'selected=selected':''}><n:flowstatus key="3"/></option>
											<option value="4" ${(status eq 4)?'selected=selected':''}><n:flowstatus key="4"/></option>
										</select>
									</label>
								</td>
								<td>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<input type="submit" class="bajy_bb_xkgl_cxan" value="查&nbsp;找">
									</label>
								</td>
							</tr>
						</table>
					</div>
					<div class="sczx_bq fl">
						<ul>
							<li class="${(empty type)?'hover':''}"><a href="${path}/service/flow/list">待办事项</a></li>
							<c:choose>
								<c:when test="${(!empty flowMainList)&&(fn:length(flowMainList)>0)}">
								<c:forEach items="${flowMainList}" var="flowType">
									<li class="${(type eq flowType.id)?'hover':''}"><a href="${path}/service/flow/list?type=${flowType.id}">${flowType.name}</a></li>
								</c:forEach>
								</c:when>
							</c:choose>
						</ul>
					</div>
					<div class="bajy_bb_xkgl_con fl">
						<div class="bajy_bb_xkgl_con1 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="120" height="40" align="center" valign="middle">类别</td>
									<td width="100" align="center" valign="middle">用户</td>
									<td width="100" align="center" valign="middle">姓名</td>
									<td width="130" align="center" valign="middle">电话</td>
									<td width="100" align="center" valign="middle">状态</td>
									<td width="140" align="center" valign="middle">创建时间</td>
									<td align="center" valign="middle" class="right-none">操作</td>
								</tr>
							</table>
						</div>
						<div class="bajy_bb_xkgl_con2 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<c:choose>
									<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
										<c:forEach items="${page.data}" var="flow">
											<tr>
												<td width="120" height="40" align="center" valign="middle">${flow.flowName}</td>
												<td width="100" align="center" valign="middle">${flow.userName}</td>
												<td width="100" align="center" valign="middle">${flow.realName}</td>
												<td width="130" align="center" valign="middle">${flow.telephone}</td>
												<td width="100" align="center" valign="middle"><n:flowstatus key="${flow.status}"/></td>
												<td width="140" align="center" valign="middle"><n:dft pattern="yyyy-MM-dd HH:mm" value="${flow.time}"/></td>
												<td align="center" valign="middle" class="right-none">
													<a href="${path}/service/flow/detail/${flow.id}">
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
								<n:paging size="10" total="${page.totalPage}" curr="${page.cpage}" href="${path}/service/flow/list?type=${type}&status=${status}&keyword=${keyword}"/>
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
