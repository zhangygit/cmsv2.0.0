var defaultTxt = '请输入关键词...';
function onFocusSearch(obj){
	if(jQuery(obj).hasClass('default_search')){
		jQuery(obj).val('');
		jQuery(obj).removeClass('default_search');
		jQuery(obj).addClass('searching');
	}
	
}
function onBlurSearch(obj){
	if(!jQuery.trim(jQuery(obj).val())){
		jQuery(obj).removeClass('searching');
		jQuery(obj).addClass('default_search');
		jQuery(obj).val(defaultTxt);
	}else{
		jQuery(obj).removeClass('default_search');
		jQuery(obj).addClass('searching');
	}
}
function cheackSearch(){
	if(jQuery('#searchKey').hasClass('default_search') || !jQuery.trim(jQuery('#searchKey').val())){
		return false;
	}
	return true;
}

var Tymb4Global=function(){
	var cms_user_key = 'AQ_AUTHENTICATION_UID_COOKIE_KEY';
	return {
		
		login : function(url){
			var username = jQuery('#username').val();
			if(!jQuery.trim(username)){
				jQuery('#username').blur();
				AppUtils.AlertBox('请输入用户名');
				return;
			}
			var password = jQuery('#password').val();
			if(!jQuery.trim(password)){
				jQuery('#password').blur();
				AppUtils.AlertBox('请输入密码');
				return;
			}
			var savelogin =  false;
		    SecurityAuthenticationController.login('', username, password, savelogin, function (resultMap) {
		        if (!resultMap) {
		            return;
		        }
		        if (resultMap["errorMsg"]) {
		        	AppUtils.AlertBox("用户名或密码错误！");
		        } else {
		        	if(url){
		        		window.location.href= url;
		        	}else{
		        		window.location.reload();
		        	}
		        }
		    });
		},
		initLoginDiv : function(returnUrl){
			UserController.getCurrUserInfo(function(data){
				var loginName = data['userName'];
	    		var userId = data['userid'];
	    		if(userId && userId != '100001'){
        		   jQuery('.login_div').hide();
        		   jQuery('#loginName').text("您好："+loginName);
        		   jQuery('.logout_div').show();
        		   jQuery('.logout_div').css('display','block');
    		   }else{
    			   jQuery('.logout_div').hide();
    			   jQuery('.login_div').show();
    		   }
	       });
		},
		loginOut : function(){
			SecurityAuthenticationController.logout(function(){
				jQuery.cookie(cms_user_key,null,{path:'/'});
				window.location.reload();
			});
		},
		//添加收藏夹
		addBookmark : function (title,url){
			if(window.sidebar){
				window.sidebar.addPanel(title,url,"");
			}else if(document.all){
				window.external.AddFavorite(url,title);
			}else if(window.opera && window.print()){
				return ture;
			} 
		},
		goBack : function(){
			window.history.go(-1);
		},
		trimWithLength : function(str,lengthLimit){
			if(!str) return '';
			str = jQuery.trim(str);
			var len = str.match(/[^ -~]/g) == null ?str.length : str.length + str.match(/[^ -~]/g).length; 
			var defaultLimit = 30;
			if(lengthLimit) defaultLimit = lengthLimit;
			if(len > defaultLimit){
				var countOfE = 0; //所有字符转换成单字符的长度
				var countOfChar = 0; //字符的长度
				for(var i = 0; i < str.length; i++ ){
					countOfChar++;
					if(str.charAt(i).match(/[^ -~]/g) == null){
						countOfE += 1;
					}else{
						countOfE += 2;
					}
					if(countOfE >= lengthLimit){
						return str.substring(0,countOfChar) + '...';
					}
				}
			}
			return str;
		},
		fCheck : function(){
			var username = jQuery('#username').val();
			if(!jQuery.trim(username)){
				AppUtils.AlertBox("请输入您的用户名");
				return false;
			}
			var password = jQuery('#password').val();
			if(!jQuery.trim(password)){
				AppUtils.AlertBox("请输入您的密码");
				return false;
			}
			return true;
		},
		doLogin : function(){
			if (!Tymb4Global.fCheck()) {
			      return false;
			   }
		   document.loginForm.submit();
		   return false;
		}
	
		
	};
	
}();
