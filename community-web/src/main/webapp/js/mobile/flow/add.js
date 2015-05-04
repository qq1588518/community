var formNum=0;
function dlefile(obj){
  $(obj).remove();
}

function addImg(){
	 formNum = formNum + 1;
	 var html = "<span id='span_"+formNum+"'>"
	  +"<input style='height: 0px;width: 0px;display: none;'  type='file' name='myfiles' id='file"+formNum+"' onchange=\"PreviewImage(this,'Img"+formNum+"','divNewPreview"+formNum+"')\"/>"
	  +"<div class='line_sty_area' style='height:200px; padding-top:20px;'  id='divNewPreview"+formNum+"' >"
	  +"<div class='line_sty_upload_text'>"
	  +"<img  onclick=\"fileclick('#file"+formNum+"');\" id='Img"+formNum+"'  class='line_sty_upload_pic' alt='上传图片' />"
	  +"	<div class='line_sty_upload_pic_clo'><img  onclick=\"dlefile('#span_"+formNum+"')\" width='100%' height='100%' src='../../../images/mobile/close.png' /></div>"      
	  +"</div>"      
	  +"</div>"  
	  +"</span>";  
       $("#mydiv").prepend(html);
}
$(function(){ 
	$("div .button").click(function(){
		var flag = valid_from();
		if(flag==true){
			var paramsIds=[];
			var paramValues=[];
			$("input[name='paramIds']").each(  
				function(){  
					paramsIds.push($(this).val());
				}  
			); 
			$("input[name='paramValues']").each(  
				function(){  
					paramValues.push($(this).val());
				}  
			); 
			var mainId =$("#mainId").val();
			var datas={};
			var values=[];
			var main={"id":mainId};
			datas.main=main;
			for(var i=0;i<paramsIds.length;i++){
				var paramId = paramsIds[i];
				var value;
				var tp	 =  $("input[name='paramTypes']")[i].value;
				if(tp==4){
					value=$.trim($("textarea[name='paramValues']")[0].value);
				}else if(tp==3){
					value = $.trim($("input[name='paramValues']")[i].value);
					if(value!=""){
						value = value.replace(/-/g,"/");
						var date = new Date(value);
						value= date.getTime();
					}
					
				}else if(tp==1){
					value = $.trim($("input[name='paramValues']")[i].value);
				}
				var obj={"value":value};  
				var para={"id":paramId};
				obj.flowParameter=para;
				values.push(obj);
				if(/^[\u4e00-\u9fa5a-zA-Z0-9\.\\*\\&\\?\:\,\-\/\。\;\'\"\‘\“\，\；\、\(\)\\{\\}\\《\\》]*$/.test(value)){
					if(value=="" || value.length>500){
						Walert("全部内容均为必填项且少于500字!");
						return;
					}
				}else{
					Walert("禁止输入特殊字符!");
					return;
				}
			}
			datas.values=values;
			progress();
			$.ajax({
				url:path+"/mobile/flow/add",
				type:"post",
				dataType:'json',
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(datas),
				success:function(res){
					var state  =res["state"];
					closeProgress();
					if(state=="success"){
						if(mainId==8){
							var commitId=res["commitId"];
							$("#commitId").val(commitId);
							$("#picForm").submit();
						}else{

							window.location.href=path+"/mobile/flow/list/mycategory/"+$("#mainId").val();
						}
					}else{
						alert("操作异常，请稍后再试...");
					}
				},error:function(){
					alert("操作异常，请稍后再试...");
				}
			});
				
				
				
		}
	});
	
	
});

function removePic(id){
	$("#pic"+id).val("");
}


function valid_from(){
	var flag=true;
	
	$("input[name='paramValues']").each(  
			function(){
				var obj = $(this).val();
				if(obj = undefined || obj == ""){
					Walert("全部内容均为必填项且少于500字!");
					flag = false; 
				}
			}  
		); 
	return flag;
//	try{
//		flag=$("#orderform").valid();
//		if(flag==true){
//			$("form[name=from_area]").each(function(){
//				var id=$(this).attr("id");
//				var mark=$("#"+id).valid();
//				var pid=$("#"+id).find("input[type=hidden][name=projectId]").val();
//				if(mark==false){
//					flag=false;
//				}else{
//					if(pid==undefined||$.trim(pid)==''){
//						$("#"+id).find("[name='projectName']").poshytip({
//							className:'tip-yellow',
//							content:"请选择项目!",
//							showTimeout:300,
//							alignTo:'target',
//							alignX:'right',
//							alignY:'center',
//							offsetX:25,
//							offsetY:20
//						}).poshytip('show');
//						flag=false;
//					}
//				}
//			});
//		}
//	}catch(e){};
}

function PreviewImage(obj, imgPreviewId, divPreviewId) {
	var allowExtention = ".jpg,.bmp,.gif,.png";
	var extention = obj.value.substring(obj.value.lastIndexOf(".") + 1)
			.toLowerCase();
	var browserVersion = window.navigator.userAgent.toUpperCase();
	if (allowExtention.indexOf(extention) > -1) {
		if (browserVersion.indexOf("MSIE") > -1) {
			if (browserVersion.indexOf("MSIE 6.0") > -1) {
				document.getElementById(imgPreviewId).setAttribute("src",
						obj.value);
			} else {
				obj.select();
				var newPreview = document.getElementById(divPreviewId + "New");
				if (newPreview == null) {
					newPreview = document.createElement("div");
					newPreview.setAttribute("id", divPreviewId + "New");
					newPreview.style.width = 160;
					newPreview.style.height = 170;
					newPreview.style.border = "solid 1px #d2e2e2";
				}
				newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"
						+ document.selection.createRange().text + "')";
				var tempDivPreview = document.getElementById(divPreviewId);
				tempDivPreview.parentNode.insertBefore(newPreview,
						tempDivPreview);
				tempDivPreview.style.display = "none";
			}
		} else if (browserVersion.indexOf("FIREFOX") > -1) {
			var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(
					/firefox\/([\d.]+)/)[1]);
			if (firefoxVersion < 7) {
				document.getElementById(imgPreviewId).setAttribute("src",
						obj.files[0].getAsDataURL());
			} else {
				document.getElementById(imgPreviewId).setAttribute("src",
						window.URL.createObjectURL(obj.files[0]));
			}
		} else if (obj.files) {

			if (typeof FileReader !== "undefined") {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.getElementById(imgPreviewId).setAttribute("src",
							e.target.result);
				}
				reader.readAsDataURL(obj.files[0]);
			} else if (browserVersion.indexOf("SAFARI") > -1) {
				alert("暂时不支持Safari浏览器!");
			}
		} else {
			document.getElementById(divPreviewId)
					.setAttribute("src", obj.value);
		}
	} else {
		alert("仅支持" + allowSuffix + "为后缀名的文件!");
		obj.value = "";
		if (browserVersion.indexOf("MSIE") > -1) {
			obj.select();
			document.selection.clear();
		}
		obj.outerHTML = obj.outerHTML;
	}
}
function fileclick(obj){
	$(obj).click();
}
