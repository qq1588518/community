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
			<div class="main" style="padding-bottom:0px;">
        	<div class="notice_fb" >公告详情</div>
            <div class="notice_conte">
            	<div class="notice_conte_con" style="width:95%;">
                	<div class="notice_conte_con_con">${notice.title }</div>
                    <div class="notice_conte_con_time"><n:dft pattern="yyyy-MM-dd" value="${notice.createTime}"/></div>
                </div>
            </div>
            <div class="notice_detil">
            	<div class="notice_detil_pic_ppic">
	            	<c:if test="${notice.img != null }">
	            	<img width="100%" src="${notice.img }" />
	            	</c:if>
                </div>
                <div class="notice_detil_Introduction">${notice.content }
            </div>
        </div>
		</div>
	</div>
</body>
</html>
