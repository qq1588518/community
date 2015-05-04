<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="project.name"/></title>
<%@include file="../../../common/include_app_meta.jsp"%>
<link href="${path}/css/mobile/index.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/mobile/common.css" rel="stylesheet" type="text/css" />
<%@include file="../../../common/include_common_script.jsp"%>
<script type="text/javascript" src="${path}/js/mobile/flow/add.js"></script>
<script type="text/javascript">
var path="${path}";
var _path="${path}";
</script>
</head>

<body>
<div class="gridContainer">
	<div id="LayoutDiv1">
		
    	<div class="main">
    	
    	
        	<div class="line_sty">
            	<div class="line_sty_name_right">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
                <div class="line_sty_midd_padding"><input type="text" class="line_sty_input" value="${user.realName}" readonly /></div>
            </div>
        	<div class="line_sty">
            	<div class="line_sty_name_right">联系电话：</div>
                <div class="line_sty_midd_padding"><input type="text" class="line_sty_input" value="${user.mobile}" readonly/></div>
            </div>
        	<div class="line_sty">
            	<div class="line_sty_name_right">身份证号：</div>
                <div class="line_sty_midd_padding"><input type="text" class="line_sty_input" value="${user.idCard}" readonly/></div>
            </div>
            
				<input type="hidden" id="mainId" value="${mainId}" />
	            <c:forEach  items="${params}" var="p">
	            	<input type="hidden" name="paramIds" value="${p.id}" />
	            	<input type="hidden" name="paramTypes" value="${p.type}" />
	            	<c:choose> 
					  <c:when test="${p.type==1}">   
					    <div class="line_sty">
			            	<div class="line_sty_name_right">${p.name }：</div>
			                <div class="line_sty_midd_padding"><input type="text" class="line_sty_input" name="paramValues" /></div>
			            </div>
					  </c:when> 
					  
					  <c:when test="${p.type==3}">   
					    <div class="line_sty">
			            	<div class="line_sty_name_right">${p.name }：</div>
			                <div class="line_sty_midd_padding"><input type="text" class="line_sty_input" name="paramValues"  readonly
			                onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d'})"/></div>
			            </div>
					  </c:when> 
					  <c:when test="${p.type==4}">   
					    <div class="line_sty_area">
			            	<div class="line_sty_name_right" style="line-height:60px;">${p.name }：</div>
			                <div class="line_sty_midd_padding" style="width:63%;padding-top:3px;"><textarea name="paramValues"  class="line_sty_areatex"></textarea></div>
			            </div>
					  </c:when> 
					</c:choose> 
	            </c:forEach>
	            
		           　<c:if test="${mainId ==8}">
					<form action="${path}/mobile/flow/upload" method="POST" enctype="multipart/form-data" id="picForm">  
						<input type="hidden" id="commitId" name="id">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' class="button01" value='增加图片' onClick='addImg()'/>
						 <div id="mydiv">
						 </div>
					</form>  
			　　</c:if> 
	            
	            <div class="all_button">
	                <div class="button" name="save">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</div>
	            </div>
            
        </div>
       
    </div>
</div>       
</body>
</html>
