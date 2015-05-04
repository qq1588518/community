<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/main/addActivities.js"></script>
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
							<li><a href="${path}/service/notice/listNotice">公告</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/activities/listActivities"  class="hover">活动</a></li>
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
		<form id="form1"   action="${path}/service/activities/modifyActivities/${activities.id}"  enctype="multipart/form-data"   method="post">
			<div class="cont1">
				<div class="xg_zhsz">
					<div class="zxxg_zhsz fl">
						<p class="xg_zhsz_bt">&nbsp;&nbsp;基本信息&nbsp;&nbsp;</p>
						<div class="zxxg_zhsz1">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="102" height="43" align="right" valign="middle">
										活动标题：
									</td>
									<td width="295">
										<label> 
											<input value="${activities.title }" id="title" placeholder="活动标题" type="text" maxlength="20" name="title" class="zhsz_1_k" />
											<label style="color: red;">*</label>
										</label>
									</td>
									<td height="43" align="right" valign="middle">活动时间：</td>
									<td colspan="4">
										<label>
										<input value="${activities.timeStr }" type="text" class="zhsz_1_k" name="time"  readonly  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'})"/>
											<label style="color: red;">*</label>
										</label>
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle">人数限制：</td>
									<td>
										<label>
											<input value="${activities.peopleLow }" id="peopleLow" name="peopleLow"  type="text"  class="khgl_frame13" />--- 
										</label>
										<label>
											<input value="${activities.peopleHigh }" id="peopleHigh" name="peopleHigh"  type="text"  class="khgl_frame13" />人<label style="color: red;">*</label>
										</label>
									</td>
									<td height="43" align="right" valign="middle">热门活动：</td>
									<td>
										<input type="radio" name="hot" id="hot_male" <c:if test="${activities.hot==1}"> checked="checked" </c:if> value="1" checked="checked" class="khgl_xz" style="float:left;" /> 
										<label for="sex_male" style="float:left;">
											是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> 
										<input <c:if test="${activities.hot==0}"> checked="checked" </c:if>  type="radio" name="hot" id="hot_female" value="0" class="khgl_xz" style="float:left;" /> 
										<label for="sex_female" style="float:left;"> 否 </label>
									</td>
									<td align="right" valign="middle">&nbsp;</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
								  <td height="43" align="right" valign="middle">图片：</td>
									<td>
									<div id="divNewPreview">
										<img src="${activities.img}" id="activitiesImg" alt="无" width="150px" height="150px">
									</div>
									</td>
								  <td height="43" align="right" valign="middle">报名人员：</td>
									<td>
									<c:forEach items="${activities.userActivitiesRelations }" var="relations" varStatus="i">
										${relations.user.realName }:${relations.user.mobile }&nbsp;<c:if test="${i.count % 3 == 0 }"><br></c:if>
									</c:forEach>
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle"></td>
									<td colspan="4">
										<label>
											<input type="file" name="file" id="file" onchange="PreviewImage(this,'activitiesImg','divNewPreview')"/> 
										</label>	
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle">内容：</td>
									<td colspan="4">
										<label>
										<textarea  class="zhsz_1_wb" rows="10" name="content" maxlength="1000" cols="90" placeholder="内容...">${activities.content}</textarea>
											<label style="color: red;">*</label>
										</label>
									</td>
								</tr>
								<tr style="height:60px;line-height: 60px;">
								<td colspan="4"  align="center" valign="middle">
										<label>
											<input type="submit" value="修&nbsp;&nbsp;改" class="khgl_cont_bc_an" /> 
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" onclick="removeActivities('${path}','${activities.id}');" value="删&nbsp;&nbsp;除" class="khgl_cont_bc_an1" /> 
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label>
										 <input type="reset" value="返&nbsp;&nbsp;回" onclick="window.history.go(-1)" class="khgl_cont_bc_an1" />
										</label>
									</td>
								</tr>
							</table>
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
