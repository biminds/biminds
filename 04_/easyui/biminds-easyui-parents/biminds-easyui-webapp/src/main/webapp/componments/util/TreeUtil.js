/**
 * zTreeUtil
 */
var ztextId, ztreeDivId, ztreeId, zlevel;
var zTreeUtil = {
		beforeClick:function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		},

		onCheck:function (e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(treeId),
				nodes = zTree.getCheckedNodes(true), v = "" ,ids = "";
			for (var i = 0, l = nodes.length; i < l; i++) {
				if(nodes[i].level == zlevel){
					v += nodes[i].name + ",";
					ids += nodes[i].id + ",";
				}
			}
			if (v.length > 0){
				v = v.substring(0, v.length - 1);
				ids = ids.substring(0, ids.length - 1);
			}
			$('#' + ztextId).val(v);
		},
		
		showMenu:function(textId, treeDivId, treeId, level) {
			ztextId = textId;
			ztreeDivId = treeDivId;
			ztreeId = treeId;
			zlevel = level;
			
			var cityOffset = $('#' + ztextId).offset();
			
			$('#'+ztreeDivId).css({left:100, top:cityOffset.top + "0"}).slideDown("fast");
			$("body").bind("mousedown", zTreeUtil.onBodyDown);
		},
		hideMenu:function() {
			$('#' + ztreeDivId).fadeOut("fast");
			$("body").unbind("mousedown", zTreeUtil.onBodyDown);
		},
		onBodyDown:function(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == ztextId || event.target.id == ztreeDivId || $(event.target).parents('#' + ztreeDivId).length>0)) {
				zTreeUtil.hideMenu();
			}
		},
		/* 
		 * 参数说明：treeId zTreeId ; level 级别
		 * Return:相应级别json数据，包含当前级别的id及name
		 * 数据格式：[{"id":"b2616678-bd8e-45c8-ab51-7a3c32c225c1","name":"奥迪A6L"}] 
		 *	
		 */
		getSelectedValueByLevel:function(treeId,level){
			var id = '',ids = [],jsonArray = '';
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj != null) {
				var nodes  = treeObj.getCheckedNodes(true);
				
				if (nodes.length > 0) {
					for (var i = 0; i < nodes.length; i++) {
						if (nodes[i].level == level) {
							var jsonObj = {};
							var treeNode = nodes[i];
							id = treeNode.id;
							name = treeNode.name;
							
							jsonObj.id = id;
							jsonObj.name = name;
							
							ids[ids.length] = jsonObj;
						}
					}
					jsonArray = JSON.stringify(ids);
				}else{
					$.messager.alert("提示", 'zTreeUtil error ： 没有选择节点！', 'error');
//					console.log("zTreeUtil message error ： 没有选择的节点！" );
				}
			}
			console.log("zTreeUtil info : 获取json数据为 " + jsonArray);
			return eval(jsonArray);
		},
		/* 
		 * 参数说明：treeId zTreeId ; level 级别
		 * Return:相应级别以上的json数据
		 * 数据格式：[{
		 * 			"id0":"3d29d740-0537-4a15-92a3-8741b335150c","name0":"2013款 320i运动设计套装 旅行版",   ----选中级别
		 * 		    "id1":"97234c47-c6bb-4180-851a-49ac4f823020","name1":"宝马3系(进口)",               ----选中级别父级别
		 * 			"id2":"272caaf0-b411-4950-8f12-91323f66dcdc","name2":"宝马(进口)",                 ----选中级别爷爷级别
		 * 			"id3":"c1ae58f2-deb0-40b8-af60-d3d644c84e2b","name3":"宝马"                       ----选中级别太爷爷级别
		 * 		  }]
		 *	
		 */
		getAllSelectedByLevel:function(treeId,level){
			var ids = [],jsonArray = '';
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj != null ) {
				var nodes  = treeObj.getCheckedNodes(true);
				if (nodes.length > 0 || level > 3) {
					for (var i = 0; i < nodes.length; i++) {
						var jsonObj = {};
						if (nodes[i].level == level) {
//							车型
							var tnode3 = nodes[i];
							var tnode0 = [],tnode1 =[],tnode2 =[];
							if(tnode3 != null)
								tnode2 = tnode3.getParentNode();
							if(tnode2 != null)
								tnode1 = tnode2.getParentNode();
							if(tnode1 != null)
								tnode0 = tnode1.getParentNode();
							
							if(tnode3 != null){
								jsonObj.id0 = tnode3.id;;
								jsonObj.name0 = tnode3.name;
							}
							if(tnode2 != null ){
								jsonObj.id1 = tnode2.id;
								jsonObj.name1 = tnode2.name;
							}
							if(tnode1 != null ){
								jsonObj.id2 = tnode1.id;
								jsonObj.name2 = tnode1.name;
							}
							if(tnode0 != null ){
								jsonObj.id3 = tnode0.id;
								jsonObj.name3 = tnode0.name;
							}
							ids[ids.length] = jsonObj;
						}
					}
					jsonArray = JSON.stringify(ids);
				}else{
					$.messager.alert("提示", 'zTreeUtil error ： 没有选择节点或level参数大于3！', 'error');
//					console.log("zTreeUtil message error ： 没有选择节点或level参数大于3！" );
				}
			}
			console.log("zTreeUtil info : 获取json数据为 " + jsonArray);
			return eval(jsonArray);
		},
		/*
		 * 参数说明：treeId---zTreeId；ids---需要设置checked的id串，以逗号分隔；b---是否为文本框setValue
		 * Return：无
		 * 调用格式：zTreeUtil.getCheckedNameByIds('treeDemo','0228ff41-4540-4c25-a1f9-4656769e354a,46d09756-401a-4397-9009-7be4d1fbb51d,dd77d20b-a8d8-4ba9-b66a-51366b185d5d',true);
		 * 
		 * */
		getCheckedNameByIds:function(treeId, ids, textId, b){
			var name = "";
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			if (treeObj != null ) {
				var nodes  = treeObj.getCheckedNodes(false);
				var id = ids.split(",");
				$.each(nodes,function(key1, value){
					$.each(id,function(key2,value){
						if(nodes[key1].id == id[key2]){
							var treeNode = nodes[key1];
//							treeNode.open = true;
							treeNode.checked = true;
							name += nodes[key1].name + ",";
							}
						});
 	 		  		});
				}
			
			if(b){
				$('#' + textId).val(name);
			}
		},
		
		getCheckedNodesByIds:function(ids, textId, nodes, b){
				var id = ids.split(",");
				var name = "";
				$.each(nodes,function(key1, value){
					$.each(id,function(key2,value){
						if(nodes[key1].id == id[key2]){
							nodes[key1].open = true;
						    nodes[key1].checked = true;
							name += nodes[key1].name + ",";
							}
						});
 	 		  		});
			
			if(b){
				$('#' + textId).val(name);
			}
			return nodes;
		},
		/*
		 * 参数说明：treeId---zTreeId；level---对应级别;numChecked---可选中多少节点
		 * Return：对应级别id，以逗号分隔的字符串
		 * 调用格式：zTreeUtil.getCheckedNameByIds('treeDemo',3);
		 * 使用场景：dataGrid表单查询
		 * 
		 * */
		getIdsByLevel:function(treeId,level,numChecked){
			var zTree = $.fn.zTree.getZTreeObj(treeId) , ids = "";
			if (zTree != null ) {
				var  nodes = zTree.getCheckedNodes(true);
				for (var i = 0, l = nodes.length; i < l; i++) {
					if(nodes[i].level == level){
						ids += nodes[i].id + ",";
					}
				}
				if(ids.split(",").length > numChecked){
					return;
				}
				if (ids.length > 0){
					ids = ids.substring(0, ids.length - 1);
				}
				
				return ids;
			}else{
				console.log('zTree 为空');
				return '';
			}
		},
		/*
		 * 参数说明：treeId---zTreeId；b---ture:全选，false：全取消选中
		 * Return：无
		 * 调用格式：zTreeUtil.getCheckedNameByIds('treeDemo',true);
		 * 使用场景：dataGrid表单Reset
		 * 
		 * */
		checkAllNodes:function(treeId,b){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			treeObj.checkAllNodes(b);
		}
	};