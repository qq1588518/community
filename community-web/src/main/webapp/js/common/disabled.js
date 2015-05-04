
$(document).ready(function(){
	/**限制输入空格[text 输入框,密码输入框]**/
	$("input[type='text']").keyup(function(e){
		var text=$(this).val();
		$(this).val(text.replace(/(^\s+)|(\s+$)/g,''));
	});
	$("input[type='text']").keydown(function(e){
		var code;
		if(window.event){
			code = e.keyCode;
		}else if(e.which){
			code = e.which;
		}
		if(code==32){
			return false;
		}else{
			return true;
		}
	});
	$("input[type='password']").keyup(function(){
		var text=$(this).val();
		$(this).val(text.replace(/(^\s+)|(\s+$)/g,''));
	});
	$("input[type='password']").keydown(function(){
		var code;
		if(window.event){
			code = e.keyCode;
		}else if(e.which){
			code = e.which;
		}
		if(code==32){
			return false;
		}else{
			return true;
		}
	});
});