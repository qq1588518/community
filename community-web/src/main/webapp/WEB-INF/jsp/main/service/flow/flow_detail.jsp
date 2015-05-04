<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/main/flowdetail.css">
    <%@include file="../../../common/include_common_script.jsp"%>
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
							<li><a href="${path}/service/flow/list" class="hover">我的工作</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/notice/listNotice"  >公告</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/activities/listActivities/"  >活动</a></li>
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
		<div class="cont1">
			<div class="xg_zhsz">
				<c:choose>
					<c:when test="${!empty brief}">
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt">&nbsp;&nbsp;基本资料&nbsp;&nbsp;</p>
							<div class="zxxg_zhsz1">
								<div class="w_main_info">
							    	<div class="w_main_info_wor">
							        	<div class="w_main_info_name">类别：</div>
							            <div class="w_main_info_mess">${brief.flowName}</div>
							        </div>
							        <div class="w_main_info_wor">
							        	<div class="w_main_info_name">状态：</div>
							            <div class="w_main_info_mess">
							            	<div class="state_${brief.status}"><n:flowstatus key="${brief.status}"/></div>
							            </div>
							        </div>
							        <div class="w_main_info_wor">
							        	<div class="w_main_info_name">申请人：</div>
							            <div class="w_main_info_mess">${brief.userName}(${brief.realName})</div>
							        </div>
							        <div class="w_main_info_wor">
							        	<div class="w_main_info_name" style="width:90px;">申请时间：</div>
							            <div class="w_main_info_mess" style="width:110px;"><n:dft pattern="yyyy-MM-dd" value="${brief.time}"/></div>
							        </div>
							    </div>
							</div>
						</div>
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt" style="margin-top: 0px;">&nbsp;&nbsp;项目类容&nbsp;&nbsp;</p>
							<div class="zxxg_zhsz1">
								<div class="w_apply">
							    	<c:if test="${(!empty paramlist)&&(fn:length(paramlist)>0)}">
							    		<c:forEach items="${paramlist}" var="obj">
							    			<div class="w_apply_helf">
									        	<div class="w_apply_title">${obj.key}：</div>
									            <div class="w_main_info_mess">
									            	<c:choose>
														<c:when test="${obj.type eq 3}">
															<n:dft pattern="yyyy-MM-dd HH:mm" value="${obj.value}"/>
														</c:when>
														<c:otherwise>
															${obj.value}
														</c:otherwise>
													</c:choose>
									            </div>
									        </div>
							    		</c:forEach>
							    	</c:if>
							    </div>
							</div>
						</div>
						<div class="zxxg_zhsz fl">
							<p class="xg_zhsz_bt" style="margin-top: 0px;">&nbsp;&nbsp;历史记录&nbsp;&nbsp;</p>
							<div class="zxxg_zhsz1">
								<c:if test="${(!empty historyList)&&(fn:length(historyList)>0)}">
									<c:forEach items="${historyList}" var="history">
										<div>
								            <div class="w_main_info">
									            <div class="w_main_info_wor">
									                <div class="w_main_info_name">操作人：</div>
									                <div class="w_main_info_mess">${history.userName}(${history.realName})</div>
									            </div>
									            <div class="w_main_info_wor">
									                <div class="w_main_info_name">电话：</div>
									                <div class="w_main_info_mess">${history.telephone}</div>
									            </div>
									            <div class="w_main_info_wor" style="width:200px;">
									                <div class="w_main_info_name" style="width:90px;">状态：</div>
									                <div class="w_main_info_mess" style="width:110px;">${history.statusName}</div>
									            </div>
									            <div class="w_main_info_wor">
									            	<div class="w_main_info_name">操作时间：</div>
									                <div class="w_main_info_mess"><n:dft pattern="yyyy-MM-dd HH:mm" value="${history.time}"/></div>
									            </div>
									         </div>
								        </div>
								        <div class="w_main_info">
								            <div class="w_main_info_name">备注：</div>
								            <div class="w_flow_remark">${history.desc}</div>
								        </div>
									</c:forEach>
								</c:if>
							</div>
						</div>
						<div class="zxxg_zhsz fl" style="background:#FFF;">
							<c:if test="${brief.status eq 1}">
								<p class="xg_zhsz_bt" style="margin-top: 0px;">&nbsp;&nbsp;审核信息&nbsp;&nbsp;</p>
							</c:if>
							<div class="zxxg_zhsz1">
								<div style="width:1000px;float:left;">
									<form action="#" id="examineForm">
									<c:if test="${brief.status eq 1}">
									    <table width="100%" border="0" cellspacing="0" cellpadding="0">
									      <tr>
									        <td width="300" height="50" align="right" valign="middle">
									        	<input type="radio" class="khgl_xz" value="2" checked="checked" name="status">
									        </td>
									        <td width="200" align="left" valign="middle">受理</td>
									        <td width="129" align="right" valign="middle">
									        	<input type="radio" class="khgl_xz" value="3" name="status">
									        </td>
									        <td align="left" valign="middle">拒绝</td>
									      </tr>
									   </table>
									    <div class="w_main_info_name" align="center" style="vertical-align: middle;line-height: 40px;">备注：</div>
								        <div class="w_flow_remark">
								        	<input class="bzsrk" style="width:100%;" type="text" name="reason" maxlength="30">
											<input type="hidden" name="id" value="${brief.id}"/>
								        </div>
							        </c:if>
							        <table width="100%" border="0" cellspacing="0" cellpadding="0">
							        	<tr align="center" valign="middle" style="height:60px;line-height: 60px;">
											<c:choose>
												<c:when test="${(brief.status) eq 1}">
													<td align="center" valign="middle">
														<label>
															<input type="submit" class="khgl_cont_bc_an" value="确&nbsp;定"/>
														</label>
													</td>
													<td align="center" valign="middle">
														<label>
															<a href="${path}/service/flow/list" class="bajy_bb_xkgl_btn_black" style="display: block;color: white;">
																返&nbsp;&nbsp;回
															</a>
														</label>
													</td>
												</c:when>
												<c:otherwise>
													<td>
														<a style="display: block;color: white;" href="${path}/service/flow/list" class="bajy_bb_xkgl_btn_black">返&nbsp;&nbsp;回</a>
													</td>
												</c:otherwise>
											</c:choose>
										</tr>
							        </table>
							        </form>
								</div>
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
											<a style="display: block;color: white;" href="${path}/service/flow/list" class="bajy_bb_xkgl_btn_black">返&nbsp;&nbsp;回</a>
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