/* 依赖参数 resourceContextPath */
var CmsVedio = function(){
	return {
		initVedioPlayer : function(basePath){
			if(jQuery("#player").length ==0) return ;
			var moviePath = jQuery("#moviePath").val();
			flowplayer( 
				    "player",  
				    basePath+"/thirdparty/flowplayer/v3.2.6/flowplayer-3.2.7.swf",
				    {
				    	plugins : {
							controls : {
								autoHide : {
									fullscreenOnly : true
								},
								tooltips : {
									buttons : true,
									play : '播放',
									fullscreen : '全屏',
									fullscreenExit : '退出全屏',
									pause : '暂停',
									mute : '静音',
									unmute : '取消静音'
								}
							}
						},
					   clip: { 
					        url: moviePath, 
					        autoPlay: false,  
					        autoBuffering: true  
					      }
				    } 
				); 
		}
		
	};
}();