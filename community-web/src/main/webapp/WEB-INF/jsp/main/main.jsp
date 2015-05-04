<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../common/include_common_script.jsp"%>
  </head>

<body>
	<div class="content">
		<div class="top">
			<div class="top1">
				<div class="top1_t">
					<div class="top1_tleft fl">
						<p class="logo3">
							<a href="${path}/manage/logout">绿地城客户管理平台——超级管理员</a>
						</p>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="#" class="hover">管理员管理</a></li>
							<li><a href="#">房源信息</a></li>
							<li><a href="#">项目管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="post" action="#">
			<div class="cont1">
				<div class="bajy_bb">
					<div class="bajy_bb_xkgl">
						<table width="100%" border="0">
							<tr>
								<td>&nbsp;&nbsp;&nbsp;</td>
							</tr>
						</table>
					</div>
					<div class="sczx_bq fl">
						<ul>
							<li class="hover"><a href="#">领导</a></li>
							<li><a href="#">案场客服</a></li>
							<li><a href="#">拓展销售客服</a></li>
							<li><a href="#">渠道管理员</a></li>
						</ul>
					</div>
					<div class="bajy_bb_xkgl_con fl">
						<div class="bajy_bb_xkgl_con1 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="40" width="100" align="center" valign="middle">用户名</td>
									<td width="95" align="center" valign="middle">姓名</td>
									<td width="96" align="center" valign="middle">密码</td>
									<td width="94" align="center" valign="middle">联系方式</td>
									<td width="93" align="center" valign="middle">管理员类型</td>
									<td width="110" align="center" valign="middle">所属项目</td>
									<td width="180" align="center" valign="middle" class="right-none">操作</td>
								</tr>
							</table>
						</div>
						<div class="bajy_bb_xkgl_con2 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">

							</table>
						</div>
					</div>
					<div class="wdfy_cont_foot fl">
					
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
