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
	</head>
<body>
	<div class="gridContainer">
		<div id="LayoutDiv1">
			<div class="main">
				<div class="all_button">
					<div class="button">我要预订</div>
				</div>
				<div class="hotel_Customer_infor">
					<div class="hotel_Customer_mation_name">杨艳</div>
					<div class="hotel_Customer_mation_ph">
						<div class="hotel_Customer_mation_ph_pic">
							<img width="100%" src="${path}/images/mobile/phone.png" />
						</div>
						<div class="hotel_Customer_mation_ph_num">13645678988</div>
					</div>
				</div>
				<div class="customer_num_bg">身份证号：110103197907235715</div>
				<div class="hotel_no">暂无预订…</div>
			</div>
		</div>
	</div>
</body>
</html>
