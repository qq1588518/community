<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../../../common/include_app_meta.jsp"%>
		<title><spring:message code="project.weixin.name"/></title>
		<link href="${path}/css/mobile/index.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/mobile/common.css" rel="stylesheet" type="text/css" />
		<%@include file="../../../common/include_common_script.jsp"%>
	</head>
<body>
	<div class="gridContainer">
		<div id="LayoutDiv1">
			<div class="main">
				<div class="notice_fb">公告详情</div>
				<c:choose>
					<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
						<c:forEach items="${page.data }" var="notice">
							<a href="${path}/mobile/notice/getNotice/${notice.id}">
								<div class="notice_conte">
									<div class="notice_conte_con">
										<div class="notice_conte_con_con">${notice.title}</div>
										<div class="notice_conte_con_time">
											<n:dft pattern="yyyy-MM-dd" value="${notice.createTime}" />
										</div>
									</div>
									<div class="notice_conte_arrow">></div>
								</div> 
							</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="notice_conte">
							<div align="center">暂无数据...</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="page">
				<n:paging curr="${page.cpage}" total="${page.totalPage}" size="${page.pageSize}" href="${path}/mobile/notice/listNotice" />
			</div>
		</div>
	</div>
</body>
</html>
