$(function(){
	$("#form1").validate({
		submitHandler:function(form){
			var flag=$("#form1").valid();
			if(flag){
				form.submit();
			}
		},
		rules: {
			"realName":{
				required: true,
				isLimit:true,
				isChinese:true,
				maxlength:5
			},
			"password":{
				minlength:6,
				maxlength:16
			},
			"chkpwd":{
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