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
    <script type="text/javascript" src="${path}/js/mobile/regist.js"></script>
  </head>
	<body>
		<div class="gridContainer">
			<div id="LayoutDiv1">
			<form id="form1" action="ownerRegister" method="post">
		    	<div class="main">
		        	<div class="line_sty">
		            	<label for="username">
			            	<div class="line_sty_name">用 户 名：</div>
			                <div class="line_sty_midd"><input name="username" id="username" type="text" class="line_sty_input" /></div>
			                <div class="line_sty_close">
			                	<a name="clear" target="username" for="username" style="cursor: pointer;">
			                		<img width="20" src="${path}/images/mobile/close.png"/>
			                	</a>
			                </div>
		                </label>
		            </div>
		        	<div class="line_sty">
			            <label for="password">
			            	<div class="line_sty_name">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</div>
			                <div class="line_sty_midd"><input name="password" id="password" type="password" class="line_sty_input" maxlength="16"/></div>
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
			                <div class="line_sty_midd"><input name="password2" id="password2" type="password" class="line_sty_input" maxlength="16"/></div>
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
			                <div class="line_sty_midd"><input name="realName" id="realName" type="text" class="line_sty_input" maxlength="5"/></div>
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
			                <div class="line_sty_midd"><input name="mobile" id="mobile"  type="text" class="line_sty_input" maxlength="11"/></div>
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
		                	<div class="line_sty_change"><input type="radio" checked="checked"  name="gender" value="1" /></div>
		                    <div class="line_sty_change_man">男</div>
		                    <div class="line_sty_change"><input type="radio" name="gender" value="0" /></div>
		                    <div class="line_sty_change_man">女</div>
		                </div>
		            </div>
		        	<div class="line_sty">
			        	<label for="email">
			            	<div class="line_sty_name">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</div>
			                <div class="line_sty_midd"><input name="email" id="email" type="text" class="line_sty_input" maxlength="30"/></div>
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
			                <div class="line_sty_midd"><input name="idCard" id="idCard" type="text" class="line_sty_input" maxlength="18"/></div>
			                <div class="line_sty_close">
			                	<a name="clear" target="idCard" for="idCard" style="cursor: pointer;">
			                		<img width="20" src="${path}/images/mobile/close.png"/>
			                	</a>
			                </div>
		                </label>
		            </div>
		        	<div class="line_sty">
		            	<div class="line_sty_name">楼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</div>
		                <div class="line_sty_midd"><input type="text" name="buildNo" class="line_sty_input" maxlength="2"/></div>
		            </div>
		        	<div class="line_sty">
		            	<div class="line_sty_name">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;元：</div>
		                <div class="line_sty_midd"><input type="text" name="unitNo" class="line_sty_input" maxlength="2"/></div>
		            </div>
		        	<div class="line_sty" style="border:none;">
		            	<div class="line_sty_name">户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
		                <div class="line_sty_midd"><input type="text" name="houseName" class="line_sty_input" maxlength="4"/></div>
		            </div>
		            <div class="all_button" align="center" style="text-align: center;">
		            	<input class="button" type="submit" value="注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册"/>
		            </div>
		        </div>
		        </form>
		    </div>
		</div>       
	</body>
</html>