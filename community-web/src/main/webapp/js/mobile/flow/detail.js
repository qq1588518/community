function deleteFlow(id){
	
	
	
	$("div[name='dialog']").show(0,function(){
		$(this).find("div[id=dialogtitle]").text("您确定要删除?");
		$("a[name=btn_confirm]").die().live("click",function(){
			$("div[name='dialog']").hide(0,function(){
				progress();
			});
		
	

		$.ajax({
			url:path+"/mobile/flow/delete/"+id,
			dataType:'json',
			success:function(res){
				var state  =res["state"];
				closeProgress();
				if(state=="success"){
					tooltip("操作成功!");
					window.location.href=path+"/mobile/flow/list/mycategory/"+$("#mainId").val();
				}else{
					tooltip("操作异常，请稍后再试...");
				}
			},error:function(){
				tooltip("操作异常，请稍后再试...");
			}
		});
		
	
		});
	});
	
	
	
	
}


$(function(){ 
	$("a[name=btn_cancel]").die().live("click",function(){
		$("div[name='dialog']").hide();
	});
});

