<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file="../../../common/include_app_meta.jsp"%>
		<title><spring:message code="project.weixin.name" /></title>
		<link href="${path}/css/mobile/index.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/mobile/common.css" rel="stylesheet" type="text/css" />
		<%@include file="../../../common/include_common_script.jsp"%>
	</head>

<body>
	<div class="gridContainer">
		<div id="LayoutDiv1">
			<div class="main" style="padding-bottom:0px;">
				<div class="notice_fb">
					<div class="notice_button1" onclick=" window.location.href='${path}/mobile/activities/toSave'">
						发起活动
					</div>
					<div class="notice_button1" style="padding-right:0px;" onclick=" window.location.href='${path}/mobile/flow/list/mycategory/10'">
						活动建议
					</div>
				</div>
				<c:choose>
					<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
						<c:forEach items="${page.data }" var="activities" varStatus="i">
							<div class="notice_conte">
								<div class="play_Scolumn_pic">
									<div class="play_Scolumn_pic_bor">
										<c:if test="${activities.img != null }">
											<img width="100%" height="100%" src="${activities.img }" />
										</c:if>
									</div>
								</div>
								<div class="play_Scolumn_con">
									<c:if test="${activities.hot == 1 }">
										<div class="play_Sco_hot">
											<img width="100%" height="100%" src="${path}/images/mobile/hot.png" />
										</div>
									</c:if>
									<div class="notice_conte_con_con" style="font-size:18px; line-height:21px;">${activities.title}</div>
									<div class="notice_conte_con_time" style="line-height:20px;">
										<n:dft pattern="yyyy-MM-dd HH:mm" value="${activities.time}" />
									</div>
									<div class="notice_conte_con_time" style="line-height:20px;">报名人数:${activities.alreadyNum}</div>
									<div class="play_Sco_btn_bor">
										<c:choose>
											<c:when test="${mak[i.index]== 1}">
												<div class="play_Sco_btn_syt01" onclick=" window.location.href='${path}/mobile/activities/getActivities/${activities.id}?mark=${mak[i.index]}'">我已报名</div>
											</c:when>
											<c:otherwise>
												<div class="play_Sco_btn_syt" onclick=" window.location.href='${path}/mobile/activities/getActivities/${activities.id}?mark=${mak[i.index]}'">我要报名</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
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
				<n:paging curr="${page.cpage}" total="${page.totalPage}" size="${page.pageSize}"
					href="${path}/mobile/activities/listActivities" />
			</div>
		</div>
	</div>
</body>
</html>
