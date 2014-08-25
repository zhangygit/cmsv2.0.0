var TreeHelper = function(){
	
	
	var constants = {
		nodeCloseClass : 'closed',
		nodeStateAttr : 'state',
		nodeLeafClass : 'leaf',
		nodeOpenClass : 'open'	
	};
	
	return {
		//依赖平台JsTreeUtil
		analyzeNode : function(node,treeId){
			var $node = jQuery(node);
			var state = $node.attr(constants.nodeStateAttr);
			var isLeaf = $node.hasClass(constants.nodeLeafClass);
			var isOpen = $node.hasClass(constants.nodeOpenClass);
			var isClose = $node.hasClass(constants.nodeCloseClass);
			node = JsTreeUtil.getJsTreeNode(node);
			node.isSelected = JsTreeUtil.isSelected(treeId,node.id);
			node.state = state;
			node.isLeaf = isLeaf;
			node.isOpen = isOpen;
			node.isClose = isClose;
			return node;
		}
	};
	
}();

