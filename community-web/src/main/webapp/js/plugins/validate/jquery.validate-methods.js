
jQuery.validator.addMethod(
	"isFloatGtZero",
	function(value, element) { 
         value=parseFloat(value); 
         return this.optional(element) || value>0;
    },
    "浮点数必须大于0"
);

// 限制特殊字符
jQuery.validator.addMethod(
	"isLimit",
	function(value, element){
		return this.optional(element)||/^[\u4e00-\u9fa5a-zA-Z0-9\.\\*\\&\\?\!\！\？\：\:\-\\/\,\，\、\。\;\；\'\"\‘\“\(\)\\{\\}\\《\\》]*$/.test(value);
	},$.validator.format("禁止输入特殊字符或空格!")
);

// 限制输入空格
jQuery.validator.addMethod(
	"Notblank",
	function(value, element){
		return this.optional(element)||!(/\s/g.test(value));
	},$.validator.format("输入字符包含空格!")
);

jQuery.validator.addMethod(
	"isFloat",
	function(value, element) {
		return this.optional(element)||/^\d{1,10}$/.test(value)||/^\d{1,8}(\.\d{1,2})?$/.test(value);
    },
    $.validator.format("只能输入整数或保留两位小数!")
);

// 折扣(打折数额)
jQuery.validator.addMethod(
	"isRebate",
	function(value, element) {
		 value=parseFloat(value);
		return this.optional(element)||(value>0&&(/^\d{1}$/.test(value)||/^\d{1}(\.\d{1,2})?$/.test(value)));
	},
	$.validator.format("只能输入[1-9]的整数或保留两位小数!")
);

// 邮政编码验证      
jQuery.validator.addMethod(
	"isZipCode", 
	function(value, element) {
  		var zip = /^[0-9]{6}$/;
  		return this.optional(element) || (zip.test(value));
	}, 
	"请正确填写您的邮政编码!"
);
