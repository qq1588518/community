<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="activity.name"/></title>
	<%@include file="../../../common/include_app_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/mobile/common.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/css/mobile/index.css"/>
	<link rel="stylesheet" href="${path}/js/common/alert/style.css" type="text/css"/>
	<script type="text/javascript" src="${path}/js/plugins/jquery/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">var _path="${path}";</script>
  </head>

<body>
	<div class="Overall_situation">
    	<img class="common_pic" src="${path}/images/mobile/change_bg.png" />
        <div class="change_bor">
        	<a href="${path}/mobile/activity/smash/100002">
	        	<div class="change_1">
	        		<img width="100%" height="100%" src="${path}/images/mobile/bottom_2.png" />
	        	</div>
        	</a>
        	<a href="${path}/mobile/activity/scratch/100001">
	            <div class="change_1" style="padding-right:0px;">
	            	<img width="100%" height="100%" src="${path}/images/mobile/bottom_1.png" />
	            </div>
            </a>
        </div>
    </div>
</body>
</html>