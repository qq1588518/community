function check_mobilephone(value){
	if($.trim(value)!=''){
		var pattern = new RegExp(/^(13|14|15|16|17|18)[0-9]{9}$/);
		return pattern.test(value);
	}else{
		return true;
	}
}
jQuery.validator.addMethod("isMobilePhone",function(value, element){
		return this.optional(element) || check_mobilephone(value);
	},$.validator.format("手机号码不合法!")
);