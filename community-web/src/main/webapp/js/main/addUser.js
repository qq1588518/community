$(function(){
	$("#form1").validate({
		submitHandler:function(form){
			var flag=$("#form1").valid();
			if(flag){
				form.submit();
			}
		},
		rules: {
			"username":{
				required: true,
				isLimit:true,
				maxlength:16,
				remote:{
					url:_path+"/manage/user/check",
					type:"post",  
	                dataType:"text",
	                data:{"username":function(){return $.trim($("#username").val())}},
	                dataFilter: function(data, type) {
	                	if(data=="true"){
	                		return true;
	                	}else{
	                		return false;
	                	}
	                }
				}
			},
			"realName":{
				required: true,
				isLimit:true,
				isChinese:true,
				maxlength:5
			},
			"password":{
				required: true,
				minlength:6,
				maxlength:16
			},
			"chkpwd":{
				required: true,
				minlength:6,
				maxlength:16,
				equalTo:"#password"
			},
			"mobile":{
				required: true,
				maxlength:11,
				isMobilePhone:true
			},
			"email":{
				email:true,
				maxlength:30
			}
		},
		messages:{
			"username":{
				required:"请输入登录用户名!",
				remote:"用户名已存在!"
			},
			"realName":{
				required:"请输入用户真实姓名!"
			},
			"password":{
				required:"请输入登录密码!"
			},
			"chkpwd":{
				required:"请输入确认密码!",
				equalTo:"两次密码输入不一致!"
			},
			"mobile":{
				required:"请输入用户手机号码!"
			},
			"email":{
				email:"邮件地址格式不合法!"
			}
		},
		showErrors:function(errorMap, errorList){
			for(var key in errorMap){
				Walert(errorMap[key]);
				break;
			}
		},
		onmouseleaver:false,
		onkeyup:false,
		onsubmit:true
	});
});