<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../common/include_core.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="../common/include_core.jsp" %>
	 <%@include file="../common/include_common_script.jsp"%>
  </head>
  <body>
  		<a href="${VirtualPath}">${VirtualPath }</a>
  		<c:forEach items="${page.data}" var="list">
  			${list.content}******
  			
  		</c:forEach>
  		<n:paging size="1" total="${page.totalPage}" curr="${page.cpage}" href="${path}/service/activities/listActivities?userId=${userId}&status=${status}&value=${value}"/>
  </body>
</html>
