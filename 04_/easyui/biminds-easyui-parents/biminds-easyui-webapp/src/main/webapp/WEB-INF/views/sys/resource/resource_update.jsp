<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!-- Tree -->
<link rel="stylesheet" href="componments/tree/css/demo.css" type="text/css">
<link rel="stylesheet" href="componments/tree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="componments/tree/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">
 	
 	var setting = {
 			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
 	
 	$(function()
 	{
 	
 		$('.ztree').each(function()
 		{
 			var zNodes = [];
 			$.ajax({
 	 			url : "sys/resource/tree",
 	 			type : "POST",
 	 			async : false,
 	 			data : {'roleId': $('#roleId').val()},
 	 			dataType:"json",  
 	 		    success: function(data){
 	 		    	console.log(data);
 	 		    	zNodes  = data;
 	 		    }
 	 		});
 			$.fn.zTree.init($('#tree_resource'), setting, zNodes);
 		});
 		
 	});
 	
</script>

	<input type="hidden" id="roleId" value="${roleId}"/>
	<!--弹出窗口开始-->
		    <div class="easyui-tabs" style="width:750px;height:250px;margin-top:-35px">
				<div title="权限列表" style="padding:10px">
			        <ul id="tree_resource" class="ztree"></ul>
		        </div>
		    </div>
		    
	<p></p>
	<div class="dlg-buttons dialog-button"  style="position: relative;margin-top: 69px;"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" id ="resourceSubmit">提交</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="EasyUiUtil.closeWindow('roleWin')" >关闭</a>
	</div>
		
	<script>
	var $resource = {
			
			resourceUpdateUrl : 'sys/resource/allocate',
			
			init : function(){
				$('#resourceSubmit').click($resource.allocatePermit);
			},
			
			//allocate authorities
			allocatePermit : function() {
				
				var authStr = '';
				var treeObj = $.fn.zTree.getZTreeObj('tree_resource');
	 			if(treeObj != null){
	 				var nodes = treeObj.getCheckedNodes(true);
	 				if(nodes.length > 0){
	 					for(var i=0;i<nodes.length;i++){
			 				authStr += nodes[i].id + ",";
			 			}
	 				}
	 			}
	 			
	 			console.log(authStr);
				if(authStr.length == 0){
					$.messager.alert('提示','请选择权限!','error');
	 				return false;
				}
				
				EasyUiUtil.showLoading("加载中..");
				$.ajax({
					type : "POST",
					async : true,
					url:  $resource.resourceUpdateUrl,
					data : {'roleId':$('#roleId').val(),'resources':authStr},
					dataType:"json",
					success:function(result)
					{
						EasyUiUtil.closeLoading();
						$("#roleWin").window('close');
					}
				});
				
			}
	};
	
	$resource.init();
	
	</script>
