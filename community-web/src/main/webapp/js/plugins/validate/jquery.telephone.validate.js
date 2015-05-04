function check_telephone(value){
	if($.trim(value)!=''){
		var pattern = new RegExp(/^(^0\d{2}-?\d{8}$)|(^0\d{3}-?\d{7}$)|(^\(0\d{2}\)-?\d{8}$)|(^\(0\d{3}\)-?\d{7}$)$/);
		return pattern.test(value);
	}else{
		return true;
	}
}
jQuery.validator.addMethod("isTelePhone",function(value, element){
		return this.optional(element) || check_telephone(value);
	},$.validator.format("电话号码不符合标准,请核对")
);