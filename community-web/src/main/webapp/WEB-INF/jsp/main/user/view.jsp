<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
							<li><a href="${path}/manage/user/list" class="hover">用户管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="get" action="${path}/manage/user/save">
			<div class="cont1">
				
			</div>
		</form>
		<!--cont end-->
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
	</body>
</html>
