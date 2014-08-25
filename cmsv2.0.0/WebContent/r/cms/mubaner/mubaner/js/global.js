var Global = function(){
	var cms_user_key = 'AQ_AUTHENTICATION_UID_COOKIE_KEY';
	return {
		
		login : function(url){
			var username = jQuery('#username').val();
			if(!jQuery.trim(username)){
				AppUtils.AlertBox('请输入用户名');
				return;
			}
			var password = jQuery('#password').val();
			if(!jQuery.trim(password)){
				AppUtils.AlertBox('请输入密码');
				return;
			}
			var savelogin =  false;
		    SecurityAuthenticationController.login('', username, password, savelogin, function (resultMap) {
		        if (!resultMap) {
		            return;
		        }
		        if (resultMap["errorMsg"]) {
		        	AppUtils.AlertBox('用户名或密码错误!');
		        } else {
		        	if(url){
		        		window.location.href= url;
		        	}else{
		        		window.location.href = '/cms';
		    	    }
		        }
		    });
		},
		initLoginDiv : function(){
			UserController.getCurrUserInfo(function(data){
    		   var loginName = data['userName'];
    		   if(loginName){
        		   jQuery('#loginName').text("您好："+loginName);
        		   jQuery('#loginIn').show();
        		   jQuery('#loginOut').hide();
        		   jQuery('#userAdmin').val(loginName);
    		   }else{
    			   jQuery('#loginIn').hide();
    			   jQuery('#loginOut').show();
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
		trim : function(str){ 
			if(!str) return ''; 
			return str.replace(/<\/?[^>]*>/gim,"").replace(/&nbsp;/g,'').replace(/(^\s+)|(\s+$)/g,""); //(^\s+)|(\s+$) 
		},
		trimWithLength : function(str,lengthLimit){ 
			if(!str) return ''; 
			str = Global.trim(str); 
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
		}
	
	};

}();
