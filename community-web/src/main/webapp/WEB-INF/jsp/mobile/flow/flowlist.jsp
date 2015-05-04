<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="project.weixin.name"/></title>
<link href="${path}/css/mobile/index.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/mobile/common.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="gridContainer">
	<div id="LayoutDiv1">
    	<div class="main">
            <div class="all_button">
            	<a href="${path}/mobile/flow/toadd/${mainId}">
	                <div class="button">
	                	<c:choose> 
						  <c:when test="${mainId==1}">   
						    我要投诉建议
						  </c:when> 
						  <c:when test="${mainId==2 || mainId==3}">   
						     我要预约
						  </c:when> 
						   <c:when test="${mainId==4}">   
						    预约保洁
						  </c:when> 
						   <c:when test="${mainId==5}">   
						    我要报修
						  </c:when> 
						   <c:when test="${mainId==6}">   
						    我要干洗
						  </c:when> 
						  <c:when test="${mainId==7}">   
						    我要租车
						  </c:when> 
						   <c:when test="${mainId==8}">   
						    房屋租赁
						  </c:when> 
						   <c:when test="${mainId==9}">   
						    房屋售卖
						  </c:when> 
						   <c:when test="${mainId==10}">   
						    活动建议
						  </c:when> 
						</c:choose> 
	                </div>
                </a>
            </div>
            <c:choose>
            	<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
            		<c:forEach  items="${page.data}" var="fb">
		            	<a href="${path}/mobile/flow/detail/${mainId}/${fb.id}">
			            	<div class="hotel_list">
				                <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                    <tr>
				                    <td width="80%" style="border-right:#838383 solid 1px;">
				                    	<div class="hotel_list_table_lef" style="border-top:none; border-bottom:1px solid #838383;">
				                        	事项：${fb.flowName }
				                        </div>
				                    </td>
				                    <td width="19%">
				                    	<div class="hotel_list_rit_top">
				                        	<div class="${fb.css}">
				                        	${fb.statusName}
				                        	</div>
				                        </div>
				                    </td>
				                  </tr>
				                    <tr>
				                    <td width="80%" style="border-right:#838383 solid 1px;">
				                    	<div class="hotel_list_tab_midl">提交时间：${fb.timeStr}</div>
				                    </td>
				                    <td width="19%">
				                    	<div class="hotel_list_rit_jr">
				                        	<div class="hotel_list_enter"><img width="14" src="${path}/images/mobile/enter.png" /></div>
				                        </div>
				                    </td>
				                  </tr>
				                </table>
				            </div>
			            </a>
		            </c:forEach>
            	</c:when>
            	<c:otherwise>
            		<div class="hotel_list">
		                <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                    <tr>
			                    <td width="100%" colspan="2" style="border-right:#838383 solid 1px;">
			                    	<div class="hotel_list_table_lef" style="border-top:none;">
			                        	<div align="center">暂无数据...</div>
			                        </div>
			                    </td>
		                  </tr>
		                </table>
		            </div>
            	</c:otherwise>
            </c:choose>
      </div>
      <div class="page">
		 <n:paging curr="${page.cpage}" total="${page.totalPage}" size="${page.pageSize}" href="${path}/mobile/flow/list/mycategory/${mainId}"/>  
	  </div>
    </div>
</div>       
</body>
</html>
