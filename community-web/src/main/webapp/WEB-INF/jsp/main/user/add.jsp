<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/main/addUser.js"></script>
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
							<li><a href="${path}/manage/user/list" class="hover">用户管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="get" action="${path}/manage/user/save">
			<div class="cont1">
				<div class="xg_zhsz">
					<div class="zxxg_zhsz fl">
						<p class="xg_zhsz_bt">&nbsp;&nbsp;基本资料&nbsp;&nbsp;</p>
						<div class="zxxg_zhsz1">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="102" height="43" align="right" valign="middle">
										用户名：
									</td>
									<td width="295">
										<label> 
											<input id="username" placeholder="用户名" type="text" maxlength="16" name="username" class="zhsz_1_k" />
											<label style="color: red;">*</label>
										</label>
									</td>
									<td height="43" align="right" valign="middle">真实姓名：</td>
									<td colspan="4">
										<label>
											<input type="text" placeholder="真实姓名" name="realName" id="realName" maxlength="5" class="zhsz_1_k" /> 
											<label style="color: red;">*</label>
										</label>
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle">密码：</td>
									<td>
										<label>
											<input maxlength="16" id="password" placeholder="密码" type="password" name="password" class="zhsz_1_k" /> 
											<label style="color: red;">*</label>
										</label>
									</td>
									<td align="right" valign="middle">确认密码：</td>
									<td colspan="4">
										<label>
											<input type="password" placeholder="确认密码" id="chkpwd" name="chkpwd" class="zhsz_1_k" maxlength="16" /> 
											<label style="color: red;">*</label>
										</label>
									</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle">性别：</td>
									<td>
										<input type="radio" name="gender" id="sex_male" value="1" checked="checked" class="khgl_xz" style="float:left;" /> 
										<label for="sex_male" style="float:left;">
											男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> 
										<input type="radio" name="gender" id="sex_female" value="0" class="khgl_xz" style="float:left;" /> 
										<label for="sex_female" style="float:left;"> 女 </label>
									</td>
									<td align="right" valign="middle">&nbsp;</td>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td height="43" align="right" valign="middle">手机号码：</td>
									<td>
										<label>
											<input type="text" maxlength="11" name="mobile" placeholder="手机号码" id="mobile" class="zhsz_1_k" />
											<label style="color: red;">*</label>	
										</label>
									</td>
									<td height="43" align="right" valign="middle">电子邮箱：</td>
									<td colspan="4">
										<input type="text" maxlength="30" name="email" id="email" class="zhsz_1_k" />
									</td>
								</tr>
								<tr style="height:60px;line-height: 60px;">
									<td colspan="3"  align="center" valign="middle">
										<label>
											<input type="submit" value="保&nbsp;&nbsp;存" class="khgl_cont_bc_an" /> 
										</label>
									</td>
									<td colspan="4" align="left" valign="middle">
										<label>
											<input type="reset" value="返&nbsp;&nbsp;回" onclick="window.location.href='${path}/manage/user/list';" class="khgl_cont_bc_an1" />
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
