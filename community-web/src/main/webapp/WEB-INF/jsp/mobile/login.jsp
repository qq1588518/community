<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/include_core.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title><spring:message code="project.weixin.name" /></title>
		<%@include file="../../common/include_app_meta.jsp"%>
		<link rel="stylesheet" type="text/css" href="${path}/css/mobile/index.css">
		<link rel="stylesheet" type="text/css" href="${path}/css/mobile/common.css">
		<%@include file="../../common/include_common_script.jsp"%>
		<script type="text/javascript" src="${path}/js/mobile/login.js"></script>
	</head>
<body>
	<div class="gridContainer">
		<div id="LayoutDiv1">
			<form id="form1" action="ownerLogin" method="post">
				<div class="main" style="padding-top:10%;">
					<div class="login_name">
						<div class="login_name_pic">
							<img width="30" src="${path}/images/mobile/name.png" />
						</div>
						<div class="login_name_input">
							<input name="username" id="username" type="text" class="line_sty_input" placeholder="请输入用户名" />
						</div>
					</div>
					<div class="login_name">
						<div class="login_name_pic">
							<img width="22" src="${path}/images/mobile/pass.png" />
						</div>
						<div class="login_name_input">
							<input name="password" id="password" type="password" class="line_sty_input" placeholder="请输入密码" />
						</div>
					</div>
					<div class="all_button">
						<div id="buttonSub" class="button">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</div>
					</div>
					<div class="all_button">
						<div onclick="javascript:window.location.href='toOwnerRegister'" class="button">我要注册</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>