$(function(){
	$("#examineForm").validate({
		submitHandler:function(form){
			var flag=$("#examineForm").valid();
			if(flag){
				flag=confirm("您确定要提交此操作?");
				var id=$("#examineForm input[type=hidden][name=id]").val();
				var status=$("#examineForm input[type=radio][name=status]:checked").val();
				var reason=$("#examineForm input[type=text][name=reason]").val();
				if(flag){
					$.ajax({
						url:_path+"/service/flow/examine",
						type:"post",
						data:{"id":id,"status":status,"reason":reason},
						dataType:"json",
						success:function(item){
							var state=item["state"];
							var message=item["message"];
							if(state=="success"){
								window.location.reload();
							}else{
								alert(message);
								window.location.reload();
							}
						},error:function(){
							alert("操作失败,稍后请重试...");
						}
					});
				}
			}
		},
		rules: {
			"status":{
				required: true
			},
			"reason":{
				required: true,
				isLimit:true,
				maxlength:30
			}
		},
		messages:{
			"status":{
				required:"请选择操作结果!"
			},
			"reason":{
				required:"请填写备注信息!"
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