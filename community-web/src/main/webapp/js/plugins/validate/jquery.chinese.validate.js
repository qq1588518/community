function check_Chinese(value){
	if($.trim(value)!=''){
		var pattern = new RegExp("^([\u4E00-\u9FA5]|[\uF900-\uFA2D]){1,}$");
		return pattern.test(value);
	}else{
		return true;
	}
}
jQuery.validator.addMethod(
	"isChinese",
	function(value, element){
		return this.optional(element) || check_Chinese(value);
	},
	$.validator.format("请输入汉字!")
);