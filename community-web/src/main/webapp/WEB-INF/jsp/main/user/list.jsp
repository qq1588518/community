<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <%@include file="../../../common/include_common_script.jsp"%>
    <script type="text/javascript">
    	$(function(){
    		$("a[name='remove']").each(function(){
    			$(this).click(function(){
    				var flag=confirm("您确定要删除此用户?");
    				if(flag){
    					var id=$(this).data("id");
    					$.ajax({
    						url:_path+"/manage/user/remove",
    						type:"post",
    						data:{"id":id},
    						dataType:"json",
    						success:function(item){
    							var state	=item["state"];
    							var message	=item["message"];
    							if(state!="success"){
    								alert(message);
    							}
    							window.location.reload();
    						},error:function(item){
    							alert("操作失败,请稍后再试...");
    							window.location.reload();
    						}
    					});
    				}
    			});
    		});
    	});
    </script>
  </head>

<body>
	<div class="content">
		<div class="top">
			<div class="top1">
				<div class="top1_t">
					<div class="top1_tleft fl">
						<p class="logo3">
							<a href="${path}/manage/logout"><spring:message code="project.name"/></a>
						</p>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/manage/user/list" class="hover">用户管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<form id="form1" name="form1" method="get" action="${path}/manage/user/list">
			<div class="cont1">
				<div class="bajy_bb">
					<div class="bajy_bb_xkgl">
						<table width="100%" border="0">
							<tr style="line-height: 50px;">
								<td valign="middle" align="left" style="vertical-align: middle;">
									&nbsp;&nbsp;
									<label>
										姓名或手机号&nbsp;：&nbsp;<input type="text" class="xg_xtxx_srk4" name="keyword" value="${keyword}">
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<input type="hidden" name="type" value="${type}"/>
										<input type="submit" class="bajy_bb_xkgl_cxan" value="查&nbsp;找">
									</label>
								</td>
								<td>
									<label>
										<a href="${path}/manage/user/add"><div class="bajy_bb_xkgl_cxan" style="width: 100px;height: 30px;">添加客服</div></a>
									</label>
								</td>
							</tr>
						</table>
					</div>
					<div class="sczx_bq fl">
						<ul>
							<li class="${(type eq 1)?'hover':''}"><a href="${path}/manage/user/list?type=1">管理员</a></li>
							<li class="${(type eq 3)?'hover':''}"><a href="${path}/manage/user/list?type=3">客服</a></li>
							<li class="${(type eq 2)?'hover':''}"><a href="${path}/manage/user/list?type=2">客户</a></li>
						</ul>
					</div>
					<div class="bajy_bb_xkgl_con fl">
						<div class="bajy_bb_xkgl_con1 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100" height="40" align="center" valign="middle">用户名</td>
									<td width="100" align="center" valign="middle">真实姓名</td>
									<td width="150" align="center" valign="middle">联系方式</td>
									<td width="200" align="center" valign="middle">电子邮箱</td>
									<td width="50" align="center" valign="middle">性别</td>
									<td width="80" align="center" valign="middle">账号状态</td>
									<td align="center" valign="middle" class="right-none">操作</td>
								</tr>
							</table>
						</div>
						<div class="bajy_bb_xkgl_con2 fl">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<c:choose>
									<c:when test="${(!empty page)&&(fn:length(page.data)>0)}">
										<c:forEach items="${page.data}" var="user">
											<tr>
												<td width="100" height="40"	 align="center" valign="middle">${user.username}</td>
												<td width="100" align="center" valign="middle">${user.realName}</td>
												<td width="150" align="center" valign="middle">${user.mobile}</td>
												<td width="200" align="center" valign="middle">${(empty user.email)?'无':user.email}</td>
												<td width="50" align="center" valign="middle">${(user.gender eq 0)?'女':'男'}</td>
												<td width="80" align="center" valign="middle">${(user.status eq 0)?'正常':'锁定'}</td>
												<td align="center" valign="middle" class="right-none">
													<c:choose>
														<c:when test="${(user.type eq 3)}">
															<a href="${path}/manage/user/${user.id}">
																<img src="${path}/images/main/sczx/gl_an1.gif">
															</a>
															&nbsp;&nbsp;&nbsp;
															<a name="remove" data-id="${user.id}" style="cursor: pointer;">
																<img src="${path}/images/main/sczx/sc.jpg">
															</a>
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="7" height="40" align="center" valign="middle">暂无数据...</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<div class="wdfy_cont_foot fl">
						<div class="page">
							<n:paging size="10" total="${page.totalPage}" curr="${page.cpage}" href="${path}/manage/user/list?type=${type}&keyword=${keyword}"/>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--cont end-->
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
	</body>
</html>
