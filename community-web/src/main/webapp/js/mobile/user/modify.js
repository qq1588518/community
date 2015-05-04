
$(function(){
	$("a[name=clear]").each(function(){
		$(this).click(function(){
			var target_id=$(this).attr("target");
			var for_id=$(this).attr("for");
			if(target_id!=undefined&&target_id!=''){
				$("#"+target_id).val("");
			}
			if(for_id!=undefined&&for_id!=''){
				$("input[name='"+for_id+"']").val("");
			}
		});
	});
	
	$("#form1").validate({
		submitHandler:function(form){
			var flag=$("#form1").valid();
			if(flag==true){
				form.submit();
			}
		},
		rules: {
			"password":{
				minlength:6,
				maxlength:16
			},
			"password2":{
				required:function(){
					var text=$("#password").val();
					if(text!=undefined&&$.trim(text)!=''){
						return true;
					}else{
						return false;
					}
				},
				minlength:6,
				maxlength:16,
				equalTo:"#password" 
			},
			"realName":{
				required: true,
				isChinese:true,
				maxlength:5
			},
			"mobile":{
				required: true,
				isMobilePhone:true
			},
			"email":{
				email:true
			},
			"idCard":{
		   		isIdCard:true
		   	},
			"gender":{
				required: true
			}
		},
		messages:{
			"password2":{
				required: "请输入确认密码!",
				equalTo: "两次输入密码不相同!"
			},
			"realName":{
				required: "请输入真实姓名",
			},
			"mobile":{
				required:"请输入手机号码!"
			},
			"email":{
				email:"邮件地址格式不正确!"
			},
			"idCard":{
		   		isIdCard:"身份证号码格式不正确!"
		   	},
			"gender":{
				required: "请选择性别!"
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