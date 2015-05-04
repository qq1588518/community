<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/main/addNotice.js"></script>
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
							<li><a href="${path}/service/flow/list"  >我的工作</a></li>
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
		<form id="form1" name="form1" method="post" action="${path}/service/notice/modifyNotice/${notice.id}" enctype="multipart/form-data" accept-charset="utf-8">
			<div class="cont1">
				<div class="xg_zhsz">
					<div class="zxxg_zhsz fl">
						<p class="xg_zhsz_bt">&nbsp;&nbsp;基本信息&nbsp;&nbsp;</p>
						<div class="zxxg_zhsz1">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="42" height="43" align="right" valign="middle">
										标题：
									</td>
									<td width="295">
										<label> 
											<input id="title" placeholder="标题" type="text" maxlength="20" name="title" value="${notice.title }" class="zhsz_1_k" />
											<label style="color: red;">*</label>
										</label>
									</td>
								</tr>
								<tr>
								  <td height="43" align="right" valign="middle">图片：</td>
									<td>
									<div id="divNewPreview">
										<img id="noticeImg" alt="无" src="${ notice.img}" width="150px" height="150px">
									</div>
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle"></td>
									<td colspan="4">
										<label>
											<input type="file" name="file" id="file" onchange="PreviewImage(this,'noticeImg','divNewPreview')"/> 
										</label>	
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="top">内容：</td>
									<td colspan="4">
										<label>
										<textarea class="zhsz_1_wb"rows="10" name="content" maxlength="250" cols="50" placeholder="内容...">${notice.content }</textarea>
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
											<input type="button" onclick="removeNotice('${path}','${notice.id}');" value="删&nbsp;&nbsp;除" class="khgl_cont_bc_an1" /> 
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
