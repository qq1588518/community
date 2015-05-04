<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.weixin.name"/></title>
    <%@include file="../../common/include_app_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/mobile/index.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/mobile/common.css">
    <%@include file="../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/mobile/user/modify.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("input[type=button][name=logout]").click(function(){
    			$("div[name='dialog']").show(0,function(){
					$(this).find("div[id=dialogtitle]").text("您确定要退出登录?");
					$("a[name=btn_confirm]").die().live("click",function(){
						window.location.href="${path}/mobile/logout";
					});
					$("a[name=btn_cancel]").die().live("click",function(){
						$("div[name='dialog']").hide();
					});
				});
    		});
    	});
    </script>
  </head>
	<body>
		<div class="gridContainer">
			<div id="LayoutDiv1">
				<form id="form1" action="${path}/mobile/owner/update" method="post">
			    	<div class="main">
			        	<div class="line_sty">
			            	<div class="line_sty_name">用 户 名：</div>
			                <div class="line_sty_midd">
			                	${user.username}
			                	<input type="hidden" name="id" value="${user.id}"/>
			                </div>
			            </div>
			        	<div class="line_sty">
				            <label for="password">
				            	<div class="line_sty_name">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</div>
				                <div class="line_sty_midd"><input name="password" id="password" type="password" value="" class="line_sty_input" maxlength="16"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="password" for="password" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
				              </label>
			            </div>
			        	<div class="line_sty">
			            	<label for="password2">
				            	<div class="line_sty_name">确认密码：</div>
				                <div class="line_sty_midd"><input name="password2" id="password2" type="password" value="" class="line_sty_input" maxlength="16"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="password2" for="password2" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
			               </label>
			            </div>
			        	<div class="line_sty">
			        		<label for="realName">
				            	<div class="line_sty_name">真实姓名：</div>
				                <div class="line_sty_midd"><input name="realName" id="realName" type="text" value="${user.realName}" class="line_sty_input" maxlength="5"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="realName" for="realName" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
			                </label>
			            </div>
			        	<div class="line_sty">
			        		<label for="mobile">
				            	<div class="line_sty_name">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</div>
				                <div class="line_sty_midd"><input name="mobile" id="mobile" value="${user.mobile}"  type="text" class="line_sty_input" maxlength="11"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="mobile" for="mobile" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
			                </label>
			            </div>
			        	<div class="line_sty">
			            	<div class="line_sty_name">性别选择：</div>
			                <div class="line_sty_midd">
			                	<div class="line_sty_change"><input type="radio" ${(user.gender eq 1)?'checked=checked':''} name="gender" value="1" /></div>
			                    <div class="line_sty_change_man">男</div>
			                    <div class="line_sty_change"><input type="radio" ${(user.gender eq 0)?'checked=checked':''} name="gender" value="0" /></div>
			                    <div class="line_sty_change_man">女</div>
			                </div>
			            </div>
			        	<div class="line_sty">
				        	<label for="email">
				            	<div class="line_sty_name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</div>
				                <div class="line_sty_midd"><input name="email" id="email" type="text" value="${user.email}" class="line_sty_input" maxlength="30"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="email" for="email" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
			                </label>
			            </div>
			        	<div class="line_sty">
				        	<label for="idCard">
				            	<div class="line_sty_name">身份证号：</div>
				                <div class="line_sty_midd"><input name="idCard" id="idCard" value="${user.idCard}" type="text" class="line_sty_input" maxlength="18"/></div>
				                <div class="line_sty_close">
				                	<a name="clear" target="idCard" for="idCard" style="cursor: pointer;">
				                		<img width="20" src="${path}/images/mobile/close.png"/>
				                	</a>
				                </div>
			                </label>
			            </div>
			            <div class="all_button" align="center" style="text-align: center;">
			            	<input class="button" type="submit" value="保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存"/>
			            </div>
			            <div class="all_button" align="center" style="text-align: center;">
			            	<input class="button" name="logout" type="button" value="退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出"/>
			            </div>
			        </div>
		        </form>
		    </div>
		</div>
		
		<div class="shadediv" name="dialog">
			<div class="contain">
				<div class="jptu1" id="dialogtitle"></div>
				<a href="javascript:void();" name="btn_confirm">
					<div class="jptu2">确认</div> 
				</a> 
				<a href="javascript:void();" name="btn_cancel">
					<div class="jptu3">取消</div> 
				</a>
			</div>
		</div>
	</body>
</html>