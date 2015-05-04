
/*
*  弹层DIV  
*  需要Jquery lib 支持
*/
function popUp(id){
	var box=$("#"+id);
	box.css("top",(($(window).height()-box.height())/2)+70+"px");
	box.css("left",(($(window).width()-box.width())/2)+150+"px");
	box.css("display","block");
	
	$(window).scroll(function(){
		var offsettop=(($(window).height()-$("#"+id).height())/2)+70+"px";
		var offsetleft=(($(window).width()-$("#"+id).width())/2)+150+"px";
		$("#"+id).css("top",offsettop);
		$("#"+id).css("left",offsetleft);
	}).resize(function(){
		var offsettop=(($(window).height()-$("#"+id).height())/2)+70+"px";
		var offsetleft=(($(window).width()-$("#"+id).width())/2)+150+"px";
		$("#"+id).css("top",offsettop);
		$("#"+id).css("left",offsetleft);
	});
}

function dialog(id){
	$("#"+id).show(function(){
		popUp(id);
	}).hide(1300);
}

function hidePop(id){
	$("#"+id).hide(1000);
}
function closePop(id){
	$("#"+id).css("display","none");
}

function getDIVLocation(id){
	window.location.href="#"+id;
//	var top=$("#"+id).offset().top;
//	var left=$("#"+id).offset().left;
//	window.scrollTo(left,top-500);
}