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
</script><script type="text/javascript" src="${path}/js/mobile/notice/addNotice.js"></script></head>

<body>
	<div class="gridContainer">
	<form id="form1"   action="${path}/mobile/notice/saveNotice"  enctype="multipart/form-data"   method="post">
		<div id="LayoutDiv1">
				<div class="main" style="background:#fff;">
            <div class="notice_conte">
            	<div class="notice_release_lin">
                	<div class="notice_release_nam">标题：</div>
                    <div class="notice_release_input">
                    	<input class="notice_release_input_sty" type="text" id="title" placeholder="标题" maxlength="20" name="title"/>
                    </div>
                </div>
<!--                 <div class="notice_release_lin"> -->
<!--                 	<div class="notice_release_nam">日期：</div> -->
<!--                     <div class="notice_release_input"> -->
<!--                     	<input class="notice_release_input_sty" type="text" /> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
            <div class="notice_conte">
					<div class="notice_release_sczp"><br>图片：</div>
					<div class="play_Scolumn_pic">
	                	<div id="divNewPreview" class="play_Scolumn_pic_bor">
	                	   <img  onclick="fileclick();" id="noticeImg"  width="100%" height="100%"</div>
						<input style="height: 0px;width: 0px;display: none;"  type="file" name="file" id="file" onchange="PreviewImage(this,'noticeImg','divNewPreview')"/>
	                	</div>
	                </div>
				</div>
            <div class="notice_conte" style="border-bottom:none;">
            	<div class="notice_release_hdgg">活动内容</div>
                <textarea class="notice_release_hdgg_input"  name="content" maxlength="1000"  placeholder="内容..."></textarea>
            </div>
            <div class="notice_button">
            	<div class="notice_button1">
                	<input type="submit" class="notice_button_sty" value="发布公告" />
                </div>
                <div class="notice_button1" style="padding-right:0px;">
                	<input type="button" class="notice_button_sty" onclick="window.history.go(-1)" value="返&nbsp;&nbsp; &nbsp;&nbsp;回" />
                </div>
            </div>
        </div>
			</div>
		</form>
	</div>
</body>
</html>
