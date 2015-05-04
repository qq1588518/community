
$(function(){
	$("#buttonSub").click(function(){
		$("#form1").submit();
	});
	$("#form1").validate({
		submitHandler:function(form){
			var flag=$("#form1").valid();
			if(flag==true){
				form.submit();
			}
		},
		rules: {
			"username":{
				required: true,
				isLimit:true,
				maxlength:16
			},
			"password":{
				required: true,
				maxlength:16
			}
		},
		messages:{
			"username":{
				required: "请输入用户名!"
			},
			"password":{
				required: "请输入登录密码!"
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