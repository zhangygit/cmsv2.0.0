var TextareaToUeditor = function() {
	var editors = {},
		toolbars = {
			'default': [['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 
			             '|', 
			             'forecolor', 'backcolor', 'insertunorderedlist', 'insertorderedlist',
			             '|',
			             'fontfamily', 'fontsize', 'paragraph',
			             '|',
			             'indent',
			             '|',
			             'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify',
			             'borizontal']]
		};
	return {
		replace: function(id, options) {
			if(editors[id]) {
				return;
			}
			jQuery('#' + id).hide();
			if(!document.getElementById(id + '_ueditor')) {
				var editorWidth = document.getElementById(id).style.width;
				jQuery('<div id="' + id + '_ueditor" style="'+ (editorWidth? 'width:' + editorWidth : '') +'"></div>').insertAfter(jQuery('#' + id));
			}
			options = options || {};
			if(!options.initialContent) {
				options.initialContent = jQuery('#' + id).val();
			}
			options.toolbar = options.toolbar || 'default';
			if(!options.toolbars && toolbars[options.toolbar]) {
				options.toolbars = toolbars[options.toolbar];
			}
			if(!options.minFrameHeight) {
				options.minFrameHeight = jQuery('#' + id).height();
			}
			if(!options.sourceEditor) {
				//查看源码的编辑器
				options.sourceEditor = 'textarea';
			}
			editors[id] = new baidu.editor.ui.Editor(options);
			editors[id].render( id + '_ueditor' );
		},
		getSource: function(id) {
			return editors[id]? editors[id].getContent() : jQuery('#' + id).val();
		},
		setSource: function(id, source) {
			editors[id]? editors[id].setContent(source) : jQuery('#' + id).val(source);
		}
	};
}();
