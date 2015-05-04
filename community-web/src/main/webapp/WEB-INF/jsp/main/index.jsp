<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/logon.css">
    <%@include file="../../common/include_common_script.jsp"%>
    <script type="text/javascript">
    	var message="${message}";
    </script>
    <script type="text/javascript" src="${path}/js/main/login.js"></script>
  </head>
  
  <body>
	<div class="home_content">
		<div class="home_nr">
			<div class="home_top fl">
				<div class="home_top_left fl">
					<a href="${path}/manage/index">
						<img border="0" src="${path}/images/main/logo.png" width="157" height="75" />
					</a>
				</div>
			</div>
			<div class="logon_con fl">
				<div class="logon_con_r fr">
					<div class="logon_con_r_1 fl"></div>
					<div class="logon_con_r_2 fr">
						<form id="form1" name="form1" method="post" action="${path}/manage/login">
							<div class="logon_con_r_2_bt fl">
								<img src="${path}/images/main/dl/glzxdl_bg.png" id="imgbt" width="218" height="41" />
							</div>
							<div class="logon_con_r_2_con fl">
								<span class="logon_con_r_2_srk fl"> 
									<label> 
										<input type="text" id="username" name="username" maxlength="16" class="logon_srk"/> 
									</label> 
								</span> 
								<br /> 
								<span class="logon_con_r_2_srk1 fl"> 
									<label> 
										<input type="password" id="password" name="password" maxlength="16" class="logon_srk"/> 
									</label> 
									<br />
									<a style="color: red"> </a> 
								</span> 
								<span class="logon_con_r_2_dl fl">
									<label> 
										<input type="submit" name="button" value="登&nbsp;&nbsp;陆" class="logon_dlan" /> 
									</label> 
								</span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="home_foot">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
</body>
</html>
