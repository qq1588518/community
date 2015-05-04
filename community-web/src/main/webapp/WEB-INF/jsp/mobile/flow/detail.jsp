<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="project.name"/></title>
<link href="${path}/css/mobile/index.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/mobile/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/plugins/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path}/js/common/alert/alert.js"></script>
<script type="text/javascript" src="${path}/js/mobile/flow/detail.js"></script>
<script type="text/javascript">
var path="${path}";
var _path="${path}";
</script>
</head>

<body>
<div class="gridContainer">
	<div id="LayoutDiv1">
    	<div class="main">
    	<input type="hidden" value="${mainId }" id="mainId">
    	
    	
    	 <c:forEach  items="${flowCommit.values}" var="v">
	    	 	
	            <c:choose> 
					  <c:when test="${v.flowParameter.type==1}">   
					    <div class="line_sty">
			            	<div class="line_sty_name_right">${v.flowParameter.name }：</div>
			                <div class="line_sty_midd_padding"><input class="line_sty_input" style="font-size:18px;color:#353536;" value="${v.value }" readonly maxlength="20"/></div>
			            </div>
					  </c:when> 
					  <c:when test="${v.flowParameter.type==3}">   
					    <div class="line_sty">
			            	<div class="line_sty_name_right">${v.flowParameter.name }：</div>
			                <div class="line_sty_midd_padding">${v.timeStr }</div>
			            </div>
					  </c:when> 
					  <c:when test="${v.flowParameter.type==4}">   
					    <div class="line_sty_area">
			            	<div class="line_sty_name_right" style="line-height:60px;">${v.flowParameter.name }：</div>
			                <div class="line_sty_midd_padding" style="width:55%; padding-top:20px; line-height:22px; overflow-y:auto;">${v.value }</div>
			            </div>
					  </c:when> 
					</c:choose> 
	            
	            
	            
	            
	            
	            
	            
    	 </c:forEach>
    	  <c:if test="${mainId == 8}">
    	  <div style="width:100%; padding-top:2px; padding-bottom:2px;">
    	  	<c:forEach items="${page.data}" var="anneximg">
    	  	<div style="width:100%; height:100%;"><img width=100%; height=100%; src="${anneximg.path}"/></div>
    	  	</c:forEach>
    	  </div>
		</c:if>
<!--     	 如果是租赁和买卖循环图片
    	<div style="width:100%; padding-top:2px; padding-bottom:2px;">
    		<div style="width:100%; height:100%;"><img width=100%; height=100%; src="${path}/images/mobile/6.png"/></div>
    		<div style="width:100%; height:100%;"><img width=100%; height=100%; src="${path}/images/mobile/6.png"/></div>
    		<div style="width:100%; height:100%;"><img width=100%; height=100%; src="${path}/images/mobile/6.png"/></div>
    		<div style="width:100%; height:100%;"><img width=100%; height=100%; src="${path}/images/mobile/6.png"/></div>
    	</div>
    	 -->
    		<c:forEach  items="${flowCommit.historys}" var="his">
	            <div class="hotel_list">
	                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                   <tr>
	                    <td height="40" colspan="2" style="border-right:#838383 solid 1px;">
	                    	<div class="hotel_list_tab_midl">
	                        	<div class="hotel_list_info" style="width:44%; font-size:14px;">&nbsp;操作人:${his.user.realName }</div>
	                        	<a href="tel:${his.user.mobile }">
		                            <div class="hotel_list_phone" style="padding-right:2px;">
		                                <input type="button" class="phone_button" value="电话：${his.user.mobile }" />
		                            </div>
	                            </a>
	                        </div>
	                    </td>
	                    <td width="55">
	                    	<div class="hotel_list_rit_top" style="border-bottom:none;">
	                        	<div class="${his.flowStatus.css }">${his.flowStatus.name }</div>
	                        </div>
	                    </td>
	                  </tr>
	                    <tr>
		                    <td width="100" height="40" align="right" valign="top" class="hotel_list_table_td_sy">操作时间：</td>
		                    <td height="40" colspan="2" class="hotel_list_table_td_sy">${his.timeStr }</td>
	                  </tr>
	                    <tr>
		                    <td height="40" align="right" valign="top" class="hotel_list_table_td_sy">备注：</td>
		                    <td height="40" colspan="2" class="hotel_list_table_td_sy" style="line-height:22px; font-size:15px; padding-right:3px;">${his.descr }</td>
	                    </tr>
	                </table>
	            </div>
        	</c:forEach>
        	
        	<c:if test="${flowCommit.flowStatus.id==1}">
        		<a href="javascript:void(0);" onclick="deleteFlow('${flowCommit.id}');">
	        		<div class="all_button">
		                <div class="button">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</div>
		            </div>
	            </a>
			</c:if>
            
   	  </div>
        
    </div>
</div>   
<div class="shadediv" name="dialog">
	<div class="main1">
		<div class="jptu1" id="dialogtitle"></div>
		<a href="javascript:void();" name="btn_confirm"><div class="jptu2">确认</div></a>
		<a href="javascript:void();" name="btn_cancel"><div class="jptu3">取消</div></a>
	</div>
</div>    
</body>
</html>
