$(document).ready(function() {
	Walert = function(title,callback) {
		if(!document.getElementById('windowalert')){
			var html="<div class='window' id='windowalert' style='display: none;'>";
			html+="<div id='alerttitle' class='alerttitle'>";
			html+="消息<span class='close' name='alertclose'></span></div>";
			html+="<div class='alertcontent'>";
			html+="<div class='message'></div>";
			html+="<input type='button' value='确定' name='windowclosebutton' name='确定' class='txtbtn'>";
			html+="</div></div>";
			$("body").append(html);
		}
		$("div[id=windowalert] .message").html(title);
		$("#windowalert").slideDown("slow");
		$("#windowalert input[name=windowclosebutton]").click(function() {
			$("#windowalert").slideUp(500,function(){
				if(callback){
					callback();
				}
				$("#windowalert").remove();
			});
		});
		$("#windowalert span[name=alertclose]").click(function() {
			$("#windowalert").slideUp(500,function(){
				$("#windowalert").remove();
			});
		});
	};
	
	Loading =function(title){
		if(!document.getElementById('windowloading')){
			var html="<div class='window' id='windowloading' style='display: none;'>";
			html+="<div id='alerttitle' class='alerttitle'>";
			html+="消息<span class='close' name='alertclose'></span></div>";
			html+="<div class='alertcontent'>";
			html+="<div align='center'><img src='"+_path+"/js/common/alert/images/loading_1.gif'/></div>";
			html+="<div class='message' align='center'>正在处理中,请稍后...</div>";
			html+="<input type='button' value='确定' name='windowclosebutton' class='txtbtn'>";
			html+="</div></div>";
			$("body").append(html);
		}
		
		$("div[id=windowloading] .message").html(title);
		$("#windowloading").slideDown("slow");
		$("#windowloading input[name=windowclosebutton]").click(function() {
			$("#windowloading").slideUp(500,function(){
				$("#windowloading").remove();
			});
		});
		$("#windowloading span[name=alertclose]").click(function() {
			$("#windowloading").slideUp(500,function(){
				$("#windowloading").remove();
			});
		});		
	};
	
	closeLoading=function(){
		$("#windowloading").remove();
	};
	
	progress = function(){
		if(!document.getElementById('progress')){
			var html = "<div class='shadediv' id='progress' style='display: none;'>";
			html+="<div class='contain'>";
			html+="<div class='jptu1' id='dialogtitle'>";
			html+="<div align='center'>";
			html+="<img src='"+_path+"/js/common/alert/images/loading_1.gif'></br>正在处理中,请稍后...</div>";
			html+="</div></div></div>";
			$("body").append(html);
		}
		$("#progress").slideDown("slow");
		$("#progress div[class='contain']").click(function(){
			$("#progress").show(0,function(){
				$(this).remove();
			});
		});
	};
	
	closeProgress = function(){
		$("#progress").remove();
	};
	
	tooltip = function(title,callback){
		if(!document.getElementById('tooltip')){
			var html="<div class='shadediv' id='tooltip' style='display: none;'>";
			html+="<div class='contain'>";
			html+="<div class='jptu1' id='dialogtitle'></div>";
			html+="</div></div>";
			$("body").append(html);
		}
		$("#tooltip div[id=dialogtitle]").html(title);
		$("#tooltip").slideDown("slow");
		$("#tooltip div[class='contain']").click(function(){
			if(callback&&(typeof(callback)=='function')){
				$("#tooltip").hide(0,function(){
					$(this).remove();
				});
				callback();
			}else{
				$("#tooltip").hide(0,function(){
					$(this).remove();
				});
			}
		});
	};
	
	Confirm	= function(target,title,sure,cancel){
		if(!document.getElementById('Confirm')){
			var html="<div id='Confirm' class='shadediv' name='dialog'>";
			html+="<div class='contain'>";
			html+="<div class='jptu1' id='dialogtitle'></div>";
			html+="<a href='javascript:void();' name='btn_confirm'>";
			html+="<div class='jptu2'>确认</div> </a> ";
			html+="<a href='javascript:void();' name='btn_cancel'>";
			html+="<div class='jptu3'>取消</div> </a>";
			html+="</div></div>";
			$("body").append(html);
		}
		$(target).click(function(){
			$("#Confirm div[name='dialog']").show(0,function(){
				if(title&&$.trim(title)!=''){
					$(this).find("div[id=dialogtitle]").text(title);
				}else{
					$(this).find("div[id=dialogtitle]").text("您确定要执行此操作吗?");
				}
				$("#Confirm a[name=btn_confirm]").die().live("click",function(){
					if(sure&&(typeof(sure)=='function')){
						sure();
					}else{
						$("#Confirm div[name='dialog']").hide(function(){
							$(this).remove();
						});
					}
				});
				$("#Confirm a[name=btn_cancel]").die().live("click",function(){
					if(cancel&&(typeof(cancel)=='function')){
						cancel();
					}else{
						$("#Confirm div[name='dialog']").hide(function(){
							$(this).remove();
						});
					}
				});
			});
		});
	}
});
