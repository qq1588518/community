function cutoff(obj){
	var len=$("#prizes_term a[name=remove]").length;
	if(len>2){
		$(obj).parent().parent().remove();
	}else{
		Walert("至少保留两个奖品项!");
	}
}
$(function(){
	$("#lottery_term input[type=text][name=radix]").on("keyup",function(){
		var text=$(this).val();
		if(text&&$.trim(text)!=''){
			$("#prizes_term font[name=base]").each(function(){
				$(this).html(text);
			});
		}else{
			$("#prizes_term font[name=base]").each(function(){
				$(this).html(100000);
			});
		}
	});
	$("a[name=add]").click(function(){
		var text	=	$("#lottery_term input[type=text][name=radix]").val();
		var len		= 	$("#prizes_term tr:gt(0)").length;
		var tr		=	$("#prizes_temp").html();
		if(len>=10){
			Walert("最多添加十个奖品项!");
		}else{
			text	=((text&&$.trim(text)!='')?text:'100000');
			tr=tr.replace("[base]",text);
			tr=tr.replace("[rank]",len+1);
			$("#prizes_term").append($(tr));
		}
	});
	
	$("#lottery_form").validate({
		submitHandler:function(form){
			var flag=$("#lottery_form").valid();
			if(flag){
				flag=false;
				var count=0;
				var lottery		=	{};
				var prizelist	=	new Array();
				var name	=$("#name").val();
				var rate	=$("#rateType option:selected").val();
				var startDate=$("#startDate").val();
				var endDate	 =$("#endDate").val();
				var radix	 =$("#radix").val();
				var descr	=$("#descr").val();
				lottery["name"]=$.trim(name);
				lottery["rate"]=parseFloat(rate);
				lottery["start_Date"]=$.trim(startDate);
				lottery["end_Date"]=$.trim(endDate);
				lottery["radix"]=parseFloat(radix);
				lottery["descr"]=$.trim(descr);
				$("#prizes_term tr:gt(0)").each(function(i,obj){
					var prizes =new Object();
					var name	=$(obj).find("input[type=text][name='Prizes.name']").val();
					var chance	=$(obj).find("input[type=text][name='chance']").val();
					var prize	=$(obj).find("select[name='prize'] option:selected").val();
					if(prize==1){
						flag=true;
					}
					count+=parseInt(chance,10);
					var rank	=$(obj).find("input[type=text][name='rank']").val();
					prizes["name"]=$.trim(name);
					prizes["chance"]=parseFloat(chance);
					prizes["prize"]=parseInt(prize,10);
					prizes["rank"]=parseInt(rank,10);
					prizelist.push(prizes);
				});
				var temp =parseInt(radix,10);
				if(temp==count){
					lottery["prizelist"]=prizelist;
					if(flag==true){
						Loading("正在提交数据,请稍后...");
						$.ajax({
							url:_path+'/service/lottery/save',
							type:'post',
							data:JSON.stringify(lottery),
							dataType:'json',
							contentType:'application/json;charset=UTF-8',
							success:function(item){
								closeLoading();
								var state	=item["state"];
								var message	=item["message"];
								if(state=="success"){
									var id = item["data"];
									if(id&&id!=undefined){
										window.location.href=_path+"/service/lottery/view/"+id;
									}else{
										window.location.href=_path+"/service/lottery/view/0";
									}
								}else{
									Walert(message);
								}
							},error:function(item){
								closeLoading();
								Walert("操作失败,请稍后再试!");
							}
						});
					}else{
						Walert("奖项至少设置一个非奖品项!");
					}
				}else{
					Walert("奖项概率总和应为1!");
				}
			}
		},
		rules: {
			"name":{
				required: true,
				isLimit:true,
				maxlength:15
			},
			"startDate":{
				required: true
			},
			"endDate":{
				required: true
			},
			"radix":{
				required: true,
				digits:true,
				min:100,
				maxlength:9
			},
			"descr":{
				required: true,
				maxlength:100
			},
			"Prizes.name":{
				required: true,
				isLimit:true,
				maxlength:20
			},
			"chance":{
				required: true,
				digits:true,
				min:1,
				maxlength:9
			},
			"rank":{
				digits:true,
				maxlength:2
			}
		},
		messages:{
			"name":{
				required:"请填写活动名称!"
			},
			"startDate":{
				required:"请填写活动开始日期!"
			},
			"endDate":{
				required: "请填写活动结束始日期!"
			},
			"radix":{
				required:"请填写活动中奖概率基数!"
			},
			"Prizes.name":{
				required:"请填写奖项名称!"
			},
			"chance":{
				required:"请填写奖项中奖概率!"
			},
			"descr":{
				required:"请填写活动描述!"
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