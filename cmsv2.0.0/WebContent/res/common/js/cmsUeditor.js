/* 依赖参数 resourceContextPath */
var CmsUeditor = function(){
	
	var defIframeId = 'info-content-frame';
	
	return {
		
		initViewContent : function(viewContent,iframeId){
			if(!iframeId) iframeId = defIframeId;
			CmsUeditor.onEditorReady(viewContent,iframeId);
		},
		onEditorReady : function(viewContent,iframeId){
			if(viewContent){
				var doc = document.getElementById(iframeId).contentWindow.document;
				doc.open();
				doc.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html><head><style>p{word-break: break-all; word-wrap:break-word;padding:0;}</style><link type="text/css" rel="stylesheet" href="/static/style/ueditor-default.css?${VERSION}"/>');
				doc.write('<script src="'+ resourceContextPath +'thirdparty/LABjs/LAB.min.js" /></script>');
				doc.write('<script src="'+ resourceContextPath +'thirdparty/jquery/jquery-1.6.2.min.js"></script>');
				doc.write('<script src="'+ resourceContextPath +'script/editor.js"></script>');
				doc.write('<script type="text/javascript"> var resourceContextPath = \''+ resourceContextPath +'\'; </script>');
				doc.write('</head>');
				doc.write('<body><textarea id="viewContent" style="display:none">'+viewContent+'</textarea><div id="ueditor-content"></div>');
				doc.write('<script type="text/javascript">document.getElementById("ueditor-content").innerHTML=(Editor.initViewHtml(jQuery("#viewContent").val()));</script></body></html>');
				doc.close();
				window.setInterval("CmsUeditor.autoTOHeight('"+iframeId+"')",200);
			}else{
				jQuery("#" + iframeId).height(0);
			}
		},
		autoTOHeight : function(iframeId){
			 var doc = document.getElementById(iframeId).contentWindow.document;
			 var height=Math.max(doc.body.scrollHeight,doc.documentElement.scrollHeight);
			 jQuery("#" + iframeId).height(doc.body.clientHeight+80);
		}
		
		
	};
	
}();