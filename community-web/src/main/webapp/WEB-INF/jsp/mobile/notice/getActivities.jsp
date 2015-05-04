<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="../../../common/include_app_meta.jsp"%>
<title><spring:message code="project.weixin.name" />
</title>
<link href="${path}/css/mobile/index.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/css/mobile/common.css" rel="stylesheet"
	type="text/css" />
	<%@include file="../../../common/include_common_script.jsp"%>
</head>

<body>
	<div class="gridContainer">
		<div id="LayoutDiv1">
			<div class="main" style="background:#fff;">
				<div class="notice_conte">
					<div class="notice_release_lin">
						<div class="notice_release_nam">标题：</div>
						<div class="notice_release_input">
							<div class="notice_release_input_sty">${activities.title }</div>
						</div>
					</div>
					<div class="notice_release_lin">
						<div class="notice_release_nam">日期：</div>
						<div class="notice_release_input">
							<input class="notice_release_input_sty" type="text"
								value="${activities.timeStr }" readonly="readonly" />
						</div>
					</div>
					<div class="notice_release_lin">
						<div class="notice_release_nam">人数：</div>
						<div class="notice_release_input">
							<input class="notice_release_input_sty" type="text"
								value="${activities.peopleLow}-${activities.peopleHigh}人"
								readonly="readonly" />
						</div>
					</div>
				</div>
				<div class="notice_conte">
					<div class="notice_release_sczp">图片：</div>
					<div class="play_Scolumn_pic">
	                	<div class="play_Scolumn_pic_bor">
	                		<c:if test="${activities.img != null }">
								<img width="100%" height="100%" src="${activities.img }" />
							</c:if>
							<c:if test="${activities.img == null }"> 无</c:if>
	                	</div>
	                </div>
				</div>
				<div class="notice_conte" style="border-bottom:none;">
					<div class="notice_release_hdgg">活动内容</div>
					<textarea class="notice_release_hdgg_input" readonly="readonly">${activities.content }</textarea>
				</div>
				<div class="notice_button">
					<c:choose>
						<c:when test="${mark == 1}">
						<div class="notice_button1">
								<input type="button"
									class="notice_button_sty01" value="我已报名" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="notice_button1">
								<input type="button"
									onclick="window.location.href='${path}/mobile/activities/joinActivities/${activities.id}'"
									class="notice_button_sty" value="我要报名" />
							</div>
						</c:otherwise>
					</c:choose>

					<div class="notice_button1" style="padding-right:0px;">
						<input type="button" onclick="window.history.go(-1)"
							class="notice_button_sty" value="返&nbsp;&nbsp; &nbsp;&nbsp;回" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
