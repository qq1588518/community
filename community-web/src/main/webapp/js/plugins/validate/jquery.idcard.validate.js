
jQuery.validator.addMethod("isIdCard",function(value, element) {
		return this.optional(element)||specialArea_simple_check(value)||simple_check(value)||isIdCard(value);// 
    },
    $.validator.format("身份证号码不合法!")
);

/**大陆地区居民身份证简单验证*/
function simple_check(person_id){
	var pattern = new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/i);
	if(person_id!=undefined&&$.trim(person_id)!=''){
		return pattern.test(person_id);
	}else{
		return true;
	}
}

/*特别行政区地区居民身份证简单验证*/
function specialArea_simple_check(person_id){
	if(person_id!=undefined&&$.trim(person_id)!=''){
		var twpattern = new RegExp(/(^[a-zA-Z][0-9]{9}$)/i);
		var hkpattern = new RegExp(/(^[A-Z][0-9]{6}[0-9A]$)/i);
		var mopattern = new RegExp(/(^[1|2][0-9]{10}[0-9A-Z])$/i);
		if(twpattern.test(person_id)){//台湾
			return true;
		}else if(hkpattern.test(person_id)){//香港
			return true;
		}else if(mopattern.test(person_id)){//澳门
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}
/**大陆地区居民身份证严格验证*/
function isIdCard(person_id) {
    var person_id = person_id;
        //身份证的地区代码对照  
        var aCity = {
            11 : "北京",
            12 : "天津",
            13 : "河北",
            14 : "山西",
            15 : "内蒙古",
            21 : "辽宁",
            22 : "吉林",
            23 : "黑龙江",
            31 : "上海",
            32 : "江苏",
            33 : "浙江",
            34 : "安徽",
            35 : "福建",
            36 : "江西",
            37 : "山东",
            41 : "河南",
            42 : "湖北",
            43 : "湖南",
            44 : "广东",
            45 : "广西",
            46 : "海南",
            50 : "重庆",
            51 : "四川",
            52 : "贵州",
            53 : "云南",
            54 : "西藏",
            61 : "陕西",
            62 : "甘肃",
            63 : "青海",
            64 : "宁夏",
            65 : "新疆",
            71 : "台湾",
            81 : "香港",
            82 : "澳门",
            91 : "国外"
        };
        //获取证件号码  
        //  var person_id=person_id.value();  
        //合法性验证  
        var sum = 0;
        //出生日期  
        var birthday;
        //验证长度与格式规范性的正则  
        var pattern = new RegExp(/(^\d{15}$)|(^\d{17}(\d|x|X)$)/i);
        if (pattern.exec(person_id)) {
            //验证身份证的合法性的正则  
            pattern = new RegExp(/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/);
            if (pattern.exec(person_id)) {
                //获取15位证件号中的出生日期并转位正常日期       
                birthday = "19" + person_id.substring(6, 8)
                        + "-" + person_id.substring(8, 10)
                        + "-" + person_id.substring(10, 12);
            } else {
                person_id = person_id.replace(/x|X$/i, "a");
                //获取18位证件号中的出生日期  
                birthday = person_id.substring(6, 10) + "-"
                        + person_id.substring(10, 12) + "-"
                        + person_id.substring(12, 14);
                //校验18位身份证号码的合法性  
                for ( var i = 17; i >= 0; i--) {
                    sum += (Math.pow(2, i) % 11)* parseInt(person_id.charAt(17 - i),11);
                }
                if (sum % 11 != 1) {
                    $(this).addClass("txtRequired");
                    //alert("身份证号码不符合国定标准，请核对！");                               
                    //$("#person_id").val("");  
                    $("#birthday").val("")
                    return false;
                }
            }
            //检测证件地区的合法性                                  
            if (aCity[parseInt(person_id.substring(0, 2))] == null) {
                $(this).addClass("txtRequired");
                //  alert("证件地区未知，请核对！");                             
                //$("#person_id").val("");  
                $("#birthday").val("");
                return false;
            }
            var dateStr = new Date(birthday.replace(/-/g, "/"));

            //alert(birthday +":"+(dateStr.getFullYear()+"-"+ Append_zore(dateStr.getMonth()+1)+"-"+ Append_zore(dateStr.getDate())))  
            if (birthday != (dateStr.getFullYear() + "-"
                    + Append_zore(dateStr.getMonth() + 1) + "-" + Append_zore(dateStr
                    .getDate()))) {
                $(this).addClass("txtRequired");
                //  alert("证件出生日期非法！");                           
                //$("#person_id").val("");  
                $("#birthday").val("");
                return false;
            }
            $(this).removeClass("txtRequired");
            $("#birthday").val(birthday);
            
        } else {
            $(this).addClass("txtRequired");
            // alert("证件号码格式非法！");                           
            //$("#person_id").val("");  
            $("#birthday").val("")
            return false;
        }
        return true;
}
function Append_zore(temp) {
    if (temp < 10) {
        return "0" + temp;
    } else {
        return temp;
    }
}