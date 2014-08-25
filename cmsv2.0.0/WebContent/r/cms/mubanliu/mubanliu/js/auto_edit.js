jQuery.cookie=function(name,value,options){if(typeof value!='undefined'){options=options||{};if(value===null){value='';options.expires=-1};var expires='';if(options.expires&&(typeof options.expires=='number'||options.expires.toUTCString)){var date;if(typeof options.expires=='number'){date=new Date();date.setTime(date.getTime()+(options.expires*24*60*60*1000))}else{date=options.expires};expires='; expires='+date.toUTCString()};var path=options.path?'; path='+(options.path):'';var domain=options.domain?'; domain='+(options.domain):'';var secure=options.secure?'; secure':'';document.cookie=[name,'=',encodeURIComponent(value),expires,path,domain,secure].join('')}else{var cookieValue=null;if(document.cookie&&document.cookie!=''){var cookies=document.cookie.split(';');for(var i=0;i<cookies.length;i++){var cookie=jQuery.trim(cookies[i]);if(cookie.substring(0,name.length+1)==(name+'=')){cookieValue=decodeURIComponent(cookie.substring(name.length+1));break}}};return cookieValue}}
function getOs(){var OsObject="";if(navigator.userAgent.indexOf("MSIE")>0){return"MSIE"};if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){return"Firefox"};if(isSafari=navigator.userAgent.indexOf("Safari")>0){return"Safari"};if(isCamino=navigator.userAgent.indexOf("Camino")>0){return"Camino"};if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){return"Gecko"}}
/*---------------------------------------------------------------------------------------------*/
var win_height = 400;
var win_width = 800;
function autoEdit(t,o,p){
	if($.cookie("XODRmivXUxmy_cookie[adminuser]")!=''&&$.cookie("XODRmivXUxmy_cookie[adminuser]")!=null){
		$.ajax({  
			url: '/include/api/auto_edit.php',  
			dataType:'html',  
			data:"action=get&t="+t+"&o="+o+"&p="+p+"&rd="+Math.random(),
			type: 'POST',  
			cache:false, 
			error: function(msg){},  
			success: function(msg){
				if(msg==0){
					
				}else{
					showEditArea(msg);
				}
			}  
		}); 
	}else{
		return false;
	}
}
function goEidt(t,o){
	return false;	
}
function showEditArea(url,height){
	sleft = ($(window).width() - win_width)/2 -10;
	$('<div id="editArea" style="top:30px;left:'+sleft+'px;background:#FFF;position:fixed;z-index:99;border:10px solid #38A0D8"><div style="width:'+win_width+'px;text-align:center;height:25px;background:#999;padding-top:5px;border-top:1px solid #000;border-bottom:1px solid #000"><input type="button" class="btn" value="点击关闭" onclick="hiddenEditArea()"/></div><iframe frameborder="0" width="'+win_width+'" height="'+win_height+'" src="'+url+'"></iframe><div style="width:'+win_width+'px;text-align:center;height:25px;background:#999;padding-top:5px;border-top:1px solid #000;border-bottom:1px solid #000"><input type="button" class="btn" value="点击关闭" onclick="hiddenEditArea()"/></div></div>').appendTo("body");
}
function hiddenEditArea(){
	$("#editArea").remove();
	window.location.reload();
}

/*---------------------------------------------------------------------------------------------*/
var menu_flag = 0;
$(document).ready(function(){
	$("#edit_logo").mouseover(function(){showEditMenu('edit_logo','config','config_set','logo','','400')}).bind("mouseleave",function(){hiddenEditMenu('edit_logo')});
	$("#edit_nav").mouseover(function(){showEditMenu('edit_nav','nav','nav_list','','','400')}).bind("mouseleave",function(){hiddenEditMenu('edit_nav')});
	$("#edit_focus").mouseover(function(){showEditMenu('edit_focus','focus','focus_set','','900','560');}).bind("mouseleave",function(){hiddenEditMenu('edit_focus')});
	$("#edit_index_about").mouseover(function(){showEditMenu('edit_index_about','about','about_set','intro','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_index_about')});
	$("#edit_foot").mouseover(function(){showEditMenu('edit_foot','config','config_set','copy_right','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_foot')});
	$("#edit_news_list").mouseover(function(){showEditMenu('edit_news_list','news','news_list','','1100','700');}).bind("mouseleave",function(){hiddenEditMenu('edit_news_list')});
	$("#edit_product_list").mouseover(function(){showEditMenu('edit_product_list','product','product_list','','1100','700');}).bind("mouseleave",function(){hiddenEditMenu('edit_product_list')});
	$("#edit_case_list").mouseover(function(){showEditMenu('edit_case_list','case','case_list','','1100','700');}).bind("mouseleave",function(){hiddenEditMenu('edit_case_list')});
	$("#edit_video_list").mouseover(function(){showEditMenu('edit_video_list','video','video_list','','1100','700');}).bind("mouseleave",function(){hiddenEditMenu('edit_video_list')});
	$("#edit_link_list").mouseover(function(){showEditMenu('edit_link_list','link','link_list','','1100','700');}).bind("mouseleave",function(){hiddenEditMenu('edit_link_list')});
	
	
	$("#edit_contact_eidt").mouseover(function(){showEditMenu('edit_contact_eidt','contact','contact_set','','1000','560');}).bind("mouseleave",function(){hiddenEditMenu('edit_contact_eidt')});
	
	$("#edit_about").mouseover(function(){showEditMenu('edit_about','about','about_set','intro','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_about')});
	
	$("#edit_job").mouseover(function(){showEditMenu('edit_job','job','job_set','','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_job')});
	$("#edit_join").mouseover(function(){showEditMenu('edit_join','join','join_set','','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_join')});
	$("#edit_salenetwork").mouseover(function(){showEditMenu('edit_salenetwork','salenetwork','salenetwork_set','','','500');}).bind("mouseleave",function(){hiddenEditMenu('edit_salenetwork')});
	$("#edit_contact").mouseover(function(){showEditMenu('edit_contact','contact','contact_set','','','560');}).bind("mouseleave",function(){hiddenEditMenu('edit_contact')});
});
function showEditMenu(obj,a,b,c,w,h){
	if($("#edit_status").val()==1){
		if(menu_flag==0){$("#"+obj).append("<span id='sEditMenu' class='s_edit_menu'><a href=\"javascript:void(0)\" onclick=\"autoEdit('"+a+"','"+b+"','"+c+"')\">编辑内容</a><br><a href=\"javascript:void(0)\" onclick=\"goEdit('"+a+"','"+b+"')\">跳转后台</a></span>");menu_flag = 1;}
		$("#sEditMenu").css({'top':'0px','right':'0px'});
		win_width = w==''?1000:w;
		win_height = h==''?400:h;
	}
}
function hiddenEditMenu(obj){
	if(menu_flag==1){
		menu_flag = 0;
		$("#sEditMenu").remove();
	}
}