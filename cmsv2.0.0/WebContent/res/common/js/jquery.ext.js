$.metadata.setType("attr", "vld");

$.validator.AlertError = {
	invalidHandler : function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			for (var name in validator.invalid) {
				alert(validator.invalid[name]);
				return;
			}
		}
	},
	showErrors : function(errors) {
	}
};
$.validator.addMethod("username", function(value) {
	if(value.length==0) {return true;}
	var p = /^[0-9a-zA-Z\u4e00-\u9fa5\.\-@_]+$/;
	return p.exec(value) ? true : false;
}, "Please enter only letters,digits,chinese and '_','-','@'");
$.validator.addMethod("path", function(value) {
	if(value.length==0) {return true;}
	var p = /^[0-9a-zA-Z]+$/;
	return p.exec(value) ? true : false;
}, "Please enter only letters and digits");

$.validator.addMethod('notCN', function(value, element){
	return this.optional(element) || /^([a-zA-z0-9]*[_]*)*$/gi.test(value);
}, "请输入输入字符、数字和“_”的组合");

$.extend($.validator.messages, {
	required : "必填",
	remote : "请修正该字段",
	email : "请输入正确格式的电子邮件",
	url : "请输入合法的网址",
	date : "请输入合法的日期",
	dateISO : "请输入合法的日期 ",
	number : "请输入合法的数字",
	digits : "只能输入整数",
	creditcard : "请输入合法的信用卡号",
	equalTo : "请再次输入相同的值",
	accept : "请输入拥有合法后缀名的字符串",
	maxlength : $.format("您最多可以输入{0} 个字符"),
	minlength : $.format("输入不能少于{0}个字符"),
	rangelength : $.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range : $.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max : $.format("该项不能大于 {0}"),
	min : $.format("该项不能小于 {0}"),
	username : "只能输入字符、数字、中文、和 _ - @ 的组合",
	path : "只能输入字符和数字的组合"
});
$.fn.extend( {
	showBy : function(target) {
		var offset = target.offset();
		var top, left;
		var b = jQuery(window).height() + jQuery(document).scrollTop() - offset.top
				- target.outerHeight();
		var t = offset.top - jQuery(document).scrollTop();
		var r = jQuery(window).width() + jQuery(document).scrollLeft() - offset.left;
		var l = offset.left + target.outerWidth() - jQuery(document).scrollLeft();
		if (b - this.outerHeight() < 0 && t > b) {
			top = offset.top - this.outerHeight() - 1;
		} else {
			top = offset.top + target.outerHeight() + 1;
		}
		if (r - this.outerWidth() < 0 && l > r) {
			left = offset.left + target.outerWidth() - this.outerWidth();
		} else {
			left = offset.left;
		}
		this.css("top", top).css("left", left).show();
	}
});
